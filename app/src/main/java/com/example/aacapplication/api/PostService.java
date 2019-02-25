package com.example.aacapplication.api;

import com.example.aacapplication.data.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {

    @GET("topics/hot.json")
    Call<List<Post>> getPostList();
}
