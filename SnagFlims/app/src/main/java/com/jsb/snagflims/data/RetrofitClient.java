package com.jsb.snagflims.data;

import com.jsb.snagflims.model.SnagFlims;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://www.snagfilms.com/";
    private static Retrofit mClient;

    RetrofitClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder buiulder = new OkHttpClient.Builder().addInterceptor(interceptor);

            mClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(buiulder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    public void getMovies(String query, ResponseCallback cb) {
        Call<SnagFlims> response = mClient.create(APIService.class).getMovies(query);
        response.enqueue(new FeaturedShowCallback(cb));
    }
}
