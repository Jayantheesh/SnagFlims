package com.jsb.sampleapplication.Data;

import com.jsb.sampleapplication.Model.ITunes;

import retrofit2.Response;

public interface ResponseCallback {
    void onSuccess(Response<ITunes> response);
    void onFailure(Throwable t);
}
