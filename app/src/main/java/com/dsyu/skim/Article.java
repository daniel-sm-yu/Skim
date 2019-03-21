package com.dsyu.skim;

import android.util.Log;
import android.widget.Toast;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Article {
    private String title;
    private String source;
    private String date;
    private String image;
    private String description;
    private String link;

    public static final String TAG = MainActivity.class.getSimpleName();

    public Article(String title, String source, String date, String image, String description, String link) {
        this.title = title;
        this.source = source;
        this.image = image;
        this.description = description;
        this.link = link;

        String year = date.substring( 0, 4 );
        String month = date.substring( 5, 7 );
        String day = date.substring( 8, 10 );

        switch (month) {
            case "01": month = "Janurary"; break;
            case "02": month = "Feburary"; break;
            case "03": month = "March"; break;
            case "04": month = "April"; break;
            case "05": month = "May"; break;
            case "06": month = "June"; break;
            case "07": month = "July"; break;
            case "08": month = "August"; break;
            case "09": month = "September"; break;
            case "10": month = "October"; break;
            case "11": month = "November"; break;
            case "12": month = "December"; break;
        }

        this.date = month + " " + day + ", " + year;

    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
