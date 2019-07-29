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

    public boolean getMovies(String query, ResponseCallback cb) {
        if (query == null || cb == null) {
            return false;
        }
        mNetClient.getMovies(query, cb);
        return true;
    }
}
