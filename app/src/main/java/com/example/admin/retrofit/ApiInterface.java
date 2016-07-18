package com.example.admin.retrofit;

import java.util.List;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 7/14/2016.
 */
public interface ApiInterface {

    @GET("posts")
    Call<ResponseBody> getPosts();

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @GET("posts/{id}")
    Call<Post> getById(@Path("id") int id);

}
