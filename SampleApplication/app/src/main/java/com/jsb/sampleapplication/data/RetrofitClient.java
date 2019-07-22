package com.jsb.sampleapplication.data;

import android.util.Log;

import com.jsb.sampleapplication.model.ITunes;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit mClient;

    private static final String BASE_URL = "https://itunes.apple.com/";
    //"https://itunes.apple.com/lookup?amgVideoId=17120"

    RetrofitClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!


        mClient =  new Retrofit.Builder().baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public void loadMovies(String id, ResponseCallback callback) {
        Call<ITunes> response =  mClient.create(APIService.class).loadMovies(id);
        response.enqueue(new ITunesCallback(callback));
    }

}

class ITunesCallback implements Callback<ITunes> {

    ResponseCallback mCb;

    public ITunesCallback(ResponseCallback callback) {
        mCb = callback;
    }

    @Override
    public void onResponse(Call<ITunes> call, Response<ITunes> response) {
        if(response.isSuccessful()) {
            Log.e("JAY", "response success");
            mCb.onSuccess(response);
        } else {
            Log.e("JAY", "response failure");
            mCb.onFailure(new Throwable("Response Failure"));
        }
    }

    @Override
    public void onFailure(Call<ITunes> call, Throwable t) {
        Log.e("JAY", "failed response");
        mCb.onFailure(t);
    }
}
