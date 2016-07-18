package com.example.admin.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 7/14/2016.
 */
public class Post {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public Post(){

    }

    public Post(int id, int userId, String title, String body) {
        this.setId(id);
        this.setUserId(userId);
        this.setTitle(title);
        this.setBody(body);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
