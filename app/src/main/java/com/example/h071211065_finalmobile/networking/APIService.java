package com.example.h071211065_finalmobile.networking;

import com.example.h071211065_finalmobile.model.Movie;
import com.example.h071211065_finalmobile.model.TVShow;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("movie/now_playing")
    Call<DataResponse<List<Movie>>> getMovies(
            @Query("api_key") String apiKey
    );

//    TV Show
    @GET("tv/popular")
    Call<DataResponse<List<TVShow>>> getTVShows(
            @Query("api_key") String apiKey
    );
}
