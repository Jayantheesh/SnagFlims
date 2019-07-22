package com.jsb.snagflims.data;

import com.jsb.snagflims.model.FeaturedShow;
import com.jsb.snagflims.model.SnagFlims;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeaturedShowCallback implements Callback<SnagFlims> {

    ResponseCallback mCb;

    public FeaturedShowCallback(ResponseCallback cb) {
        mCb = cb;
    }

    @Override
    public void onResponse(Call<SnagFlims> call, Response<SnagFlims> response) {
        if (response.isSuccessful()) {
            mCb.onSucess(response);
        } else {
            mCb.onFailure("Failed to get the data");
        }
    }

    @Override
    public void onFailure(Call<SnagFlims> call, Throwable t) {
        mCb.onFailure(t.getMessage());
    }
}
