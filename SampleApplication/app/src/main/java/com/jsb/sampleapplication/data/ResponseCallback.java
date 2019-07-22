package com.jsb.sampleapplication.data;

import com.jsb.sampleapplication.model.ITunes;

import retrofit2.Response;

public interface ResponseCallback {
    void onSuccess(Response<ITunes> response);
    void onFailure(Throwable t);
}
