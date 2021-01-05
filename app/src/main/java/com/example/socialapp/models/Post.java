package com.example.socialapp.models;

public class Post {
    private String text;
    private User user;
    private long currentTime;
    public Post(String text, User user, long currentTime) {
        this.text=text;
        this.user=user;
        this.currentTime=currentTime;
    }

    public Post(){

    }

    public String getText(){
        return this.text;
    }

    public User getUser(){
        return this.user;
    }

    public long getCurrentTime() {
        return currentTime;
    }
}
