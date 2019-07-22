package com.jsb.snagflims.data;

import android.content.Context;

public class DataRepo {

    private static DataRepo mDatarepo;
    private RetrofitClient mNetClient;
    private Context mContext;

    private DataRepo (Context context) {
        mContext = context;
        mNetClient = new RetrofitClient();
    }

    public static DataRepo getInstance(Context context) {
        if (mDatarepo == null) {
            mDatarepo = new DataRepo(context);
        }
        return mDatarepo;
    }

    public void getMovies(String query, ResponseCallback cb) {
        mNetClient.getMovies(query, cb);
    }
}
