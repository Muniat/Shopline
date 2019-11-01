package com.example.shopline.View;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private int id;
    private String name;

    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
