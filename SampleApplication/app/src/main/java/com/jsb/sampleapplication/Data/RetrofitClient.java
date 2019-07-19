package com.jsb.sampleapplication.Data;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit mClient = null;

    private static final String BASE_URL = "https://itunes.apple.com/";
    //"https://itunes.apple.com/lookup?amgVideoId=17120"

    private static Retrofit getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!


        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofitClient() {
        if (mClient == null) {
            mClient = getClient();
        }
        Log.e("JAY", "getRetrofitClient");
        return mClient;
    }


}
