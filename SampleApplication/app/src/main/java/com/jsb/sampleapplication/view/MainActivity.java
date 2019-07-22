package com.jsb.sampleapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jsb.sampleapplication.data.Repo;
import com.jsb.sampleapplication.data.ResponseCallback;
import com.jsb.sampleapplication.model.ITunes;
import com.jsb.sampleapplication.R;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ResponseCallback {

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

        Repo.getInstance(getApplicationContext()).loadMovies("17120", this);
    }

    @Override
    public void onSuccess(Response<ITunes> response) {
        mRVAdapter = new RVAdapter(response.body(), this);
        mRecyclerView.setAdapter(mRVAdapter);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }
}
