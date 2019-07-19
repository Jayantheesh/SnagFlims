package com.jsb.sampleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ITunes> {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRVAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the Recycler view
        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Controller controller = new Controller();
        controller.start();
        Call<ITunes> call = controller.getAPIService().loadMovies("17120");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ITunes> call, Response<ITunes> response) {
        if(response.isSuccessful()) {
            ITunes iTunes = response.body();
            System.out.println(iTunes.getResultCount());
            Log.e("Jayantheesh", String.valueOf(iTunes.getResultCount()));
            mRVAdapter = new RVAdapter(iTunes, this);
            mRecyclerView.setAdapter(mRVAdapter);
        } else {
            Toast.makeText(this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ITunes> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
