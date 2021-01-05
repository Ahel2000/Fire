package com.example.socialapp.models;


public class User {
    private String uid;
    private String displayName;
    private String displayUrl;

    public User(String uid, String displayName, String displayUrl){
        this.uid=uid;
        this.displayName=displayName;
        this.displayUrl=displayUrl;
    }

    public User(){

    }

    public String getUid(){
        return this.uid;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public String getDisplayUrl(){
        return this.displayUrl;
    }

}
