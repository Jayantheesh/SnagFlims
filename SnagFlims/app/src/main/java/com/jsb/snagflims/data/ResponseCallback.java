package com.jsb.snagflims.data;

import com.jsb.snagflims.model.SnagFlims;

import retrofit2.Response;

public interface ResponseCallback {

    void onSucess(Response<SnagFlims> response);
    void onFailure(String msg);
}
