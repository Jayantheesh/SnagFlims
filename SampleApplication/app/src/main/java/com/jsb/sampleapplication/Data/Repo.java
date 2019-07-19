package com.jsb.sampleapplication.Data;


import android.util.Log;

import com.jsb.sampleapplication.Model.ITunes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repo implements Callback<ITunes> {

    private static Repo mRepo = null;
    private ResponseCallback mContext;

    private Repo(ResponseCallback context) {
        mContext= context;
    }

    public static Repo getInstance(ResponseCallback context) {
        if (mRepo == null) {
            mRepo = new Repo(context);
        }
        return mRepo;
    }

    public void loadMovies(String id) {
        Call<ITunes> response =  RetrofitClient.getRetrofitClient().create(APIService.class).loadMovies(id);
        response.enqueue(this);
    }

    @Override
    public void onResponse(Call<ITunes> call, Response<ITunes> response) {
        if(response.isSuccessful()) {
            Log.e("JAY", "response success");
            mContext.onSuccess(response);
        } else {
            Log.e("JAY", "response failure");
            mContext.onFailure(new Throwable("Response Failure"));
        }
    }

    @Override
    public void onFailure(Call<ITunes> call, Throwable t) {
        Log.e("JAY", "failed response");
        mContext.onFailure(t);
    }
}
