package com.jsb.snagflims.data;

import com.jsb.snagflims.model.SnagFlims;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("apis/android/featured/shows.json")
    Call<SnagFlims> getMovies(@Query("site") String flims);

}
