package com.dsyu.skim;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private AllArticles allArticles;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_back:

                    return true;
                case R.id.navigation_read:

                    return true;
                case R.id.navigation_next:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        BottomNavigationView navigation = (BottomNavigationView) findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );

        getNews( "fifa" );
    }

    private void getNews(String topic) {

        String apiKEY = BuildConfig.skim_key;

        String newsURL = "https://newsapi.org/v2/everything?apiKey=" + apiKEY + "&q=" + topic;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(newsURL).build();

        Call call = client.newCall( request );

        call.enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {

                        setAllArticles(jsonData);
//                        displayCurrentArticle();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    private void setAllArticles(String jsonData) throws JSONException {
        JSONObject everyArticle = new JSONObject( jsonData );
    }


}