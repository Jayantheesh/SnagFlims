package com.jsb.sampleapplication;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<ITunes> {

    static final String BASE_URL = "https://itunes.apple.com/";
    //"https://itunes.apple.com/lookup?amgVideoId=17120"


    public void start(String id) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService =  retrofit.create(APIService.class);
        Call<ITunes> call = apiService.loadMovies(id);

        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ITunes> call, Response<ITunes> response) {
        if(response.isSuccessful()) {
            ITunes responseList = response.body();
            System.out.println(responseList.getResultCount());
            Log.e("Jayantheesh", String.valueOf(responseList.getResultCount()));
            //responseList.forEach(res -> System.out.println(res.getResultCount()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ITunes> call, Throwable t) {
        t.printStackTrace();
        Log.e("Jayantheesh", "onFailure");
    }
}
