package com.dsyu.skim;

import java.net.URL;
import java.util.Date;

public class Article {
    private String title;
    private String source;
    private String date;
    private String image;
    private String description;
    private String link;


    public Article(String title, String source, String date, String image, String description, String link) {
        this.title = title;
        this.source = source;
        this.date = date;
        this.image = image;
        this.description = description;
        this.link = link;
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
