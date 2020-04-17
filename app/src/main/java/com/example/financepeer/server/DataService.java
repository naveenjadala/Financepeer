package com.example.financepeer.server;

import com.example.financepeer.model.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    // to get the list of movies.
    @GET("movie?sort_by=popularity.desc&api_key=fd75d8c708d418f9ee6280f179e7f399")
    Call<MoviesModel> getMovieList();
}
