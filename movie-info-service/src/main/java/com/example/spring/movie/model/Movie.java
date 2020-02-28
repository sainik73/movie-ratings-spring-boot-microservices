package com.example.spring.movie.model;

public class Movie {

    public Movie(String name, String title, String summary) {
        this.name = name;
        this.title = title;
        this.summary = summary;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    private String name;
    private String title;
    private String summary;
}
