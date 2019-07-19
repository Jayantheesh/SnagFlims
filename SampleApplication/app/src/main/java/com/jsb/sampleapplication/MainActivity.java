package com.jsb.sampleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ITunes> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller controller = new Controller();
        controller.start("17120");
        Call<ITunes> call = controller.getAPIService().loadMovies("17120");

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
