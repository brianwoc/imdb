package com.example.imdb.controller;

public class MyModelObject {
    private String title;

    public MyModelObject(String title) {
        this.title = title;
    }

    public MyModelObject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
