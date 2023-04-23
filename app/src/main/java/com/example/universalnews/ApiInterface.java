package com.example.universalnews;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiInterface {

    @GET("top-headlines")
    Call<NewsResponse> getNewsByGenres(
            @Query("country") String country,
            @Query("pageSize") ArrayList<String> pagesize,
            @Query("apiKey") String apiKey);
    @GET("top-headlines")
    Call<NewsResponse> getCategoryNews(
            @Query("country") String country,
            @Query("pageSize") int pagesize,
            @Query("category") String category,
            @Query("apiKey") String apiKey);

}
