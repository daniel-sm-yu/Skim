package com.dsyu.skim;

import java.net.URL;
import java.util.Date;

public class Article {
    private String title;
    private String source;
    private Date date;
    private URL image;
    private String description;
    private URL link;


    public Article(String title, String source, Date date, URL image, String description, URL link) {
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

    public Date getDate() {
        return date;
    }

    public URL getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public URL getLink() {
        return link;
    }
}
