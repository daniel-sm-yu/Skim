package com.dsyu.skim;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public AllArticles allArticles = new AllArticles();
    public TextView titleTextView;
    public TextView sourceTextView;
    public TextView dateTextView;
    public ImageView articleImageView;
    public TextView descriptionTextView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_back:
                    displayArticle( allArticles.getPreviousArticle() );
                    return true;

                case R.id.navigation_read:
                    // Opens link to article
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(allArticles.getCurrentArticle().getLink()));
                    startActivity(browserIntent);
                    return true;

                case R.id.navigation_next:
                    displayArticle( allArticles.getNextArticle() );
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        BottomNavigationView navigation = findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        MaterialSearchBar searchBar = findViewById( R.id.searchBar );

        getNews( "tech" );

        searchBar.setOnSearchActionListener( new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) { }
            @Override
            public void onButtonClicked(int buttonCode) { }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                getNews( String.valueOf( text ) );
                // Hides keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } );
    }

    private void getNews(String topic) {

        String apiKEY = BuildConfig.skim_key;
        String newsURL = "https://newsapi.org/v2/everything?language=en&apiKey=" + apiKEY + "&q=";

        StringTokenizer topics = new StringTokenizer(topic);
        while (topics.hasMoreTokens()) {
            newsURL += topics.nextToken() + "+";
        }

        Log.e(TAG, newsURL );

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(newsURL).build();
        Call call = client.newCall( request );

        call.enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        setAllArticles(jsonData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    private void setAllArticles(String jsonData) throws JSONException {
        JSONObject jsonResponse = new JSONObject( jsonData );
        JSONArray articles = jsonResponse.getJSONArray( "articles" );
        int numOfResponses = jsonResponse.getInt( "totalResults" );

        if (numOfResponses == 0) {
            runOnUiThread( new Runnable() {
                @Override
                public void run() {
                    Toast.makeText( MainActivity.this, "No results found.", Toast.LENGTH_LONG ).show();
                }
            } );
            return;
        }

        allArticles.setNumOfArticles( numOfResponses );

        for (int i = 0; i < allArticles.getNumOfArticles(); i++) {
            JSONObject currentArticle = articles.getJSONObject( i );

            Article article = new Article(
                emptyCheck( currentArticle.getString( "title" ) ),
                emptyCheck( currentArticle.getJSONObject( "source" ).getString( "name" ) ),
                emptyCheck( currentArticle.getString( "publishedAt" ) ),
                emptyCheck( currentArticle.getString( "urlToImage" ) ),
                emptyCheck( currentArticle.getString( "description" ) ),
                emptyCheck( currentArticle.getString( "url" ) )
            );
            allArticles.setArticleAtIndex( i, article );
        }
        displayArticle(allArticles.getCurrentArticle());
    }

    private void displayArticle(final Article article) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                titleTextView = findViewById( R.id.titleTextView );
                sourceTextView = findViewById( R.id.sourceTextView );
                dateTextView = findViewById( R.id.dateTextView );
                articleImageView = findViewById( R.id.articleImageView);
                descriptionTextView = findViewById( R.id.descriptionTextView );

                titleTextView.setText( article.getTitle() );
                sourceTextView.setText( article.getSource() );
                dateTextView.setText( article.getDate() );
                Picasso.with(getApplicationContext()).load(article.getImage()).into(articleImageView);
                descriptionTextView.setText( article.getDescription() );
            }
        } );
    }

    private String emptyCheck(String value) {
        if (value.isEmpty()) {
            return null;
        }
        else {
            return value;
        }

    }
}