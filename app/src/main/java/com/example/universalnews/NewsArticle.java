package com.example.universalnews;

public class NewsArticle {
    private String title;
    private String description;
    private String imageUrl;
    private String date;
    private String source;

    public NewsArticle(String title, String description, String imageUrl, String date, String source) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }
}
