package com.jsb.sampleapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("lookup")
    Call<ITunes> loadMovies(@Query("amgVideoId") String id);
}
