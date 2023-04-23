package com.example.universalnews;

public class Genre implements Comparable<Genre> {
    private String name;
    private boolean checked;

    public Genre(String name,boolean checked) {
        this.name = name;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    // Implement Comparable interface to sort genres alphabetically
    @Override
    public int compareTo(Genre other) {
        return this.name.compareTo(other.name);
    }
}
