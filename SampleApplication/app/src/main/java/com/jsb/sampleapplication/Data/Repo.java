package com.jsb.sampleapplication.Data;


import android.content.Context;

public class Repo {

    private static Repo mRepo = null;
    private Context mContext;

    RetrofitClient mNetworkClient;

    private Repo(Context context) {
        mContext= context;
        mNetworkClient = new RetrofitClient();
    }

    public static Repo getInstance(Context context) {
        if (mRepo == null) {
            mRepo = new Repo(context);
        }
        return mRepo;
    }

    public void loadMovies(String id, ResponseCallback callback) {
        mNetworkClient.loadMovies(id, callback);
    }


}
