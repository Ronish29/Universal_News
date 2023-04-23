package com.example.universalnews;

public class MyData {
    public String title;
    public String genre;
    public boolean selected;

    public MyData(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.selected = false;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

