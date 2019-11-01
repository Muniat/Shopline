package com.example.shopline.Controller;

import com.example.shopline.View.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI {
    @GET("posts")
    Call<List<Post>> getPosts();


}
