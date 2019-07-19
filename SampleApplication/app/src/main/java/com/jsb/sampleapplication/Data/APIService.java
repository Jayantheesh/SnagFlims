package com.jsb.sampleapplication.Data;

import com.jsb.sampleapplication.Model.ITunes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("lookup")
    Call<ITunes> loadMovies(@Query("amgVideoId") String id);
}
