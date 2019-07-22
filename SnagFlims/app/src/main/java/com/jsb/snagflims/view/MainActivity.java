package com.jsb.snagflims.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jsb.snagflims.R;
import com.jsb.snagflims.data.DataRepo;
import com.jsb.snagflims.data.ResponseCallback;
import com.jsb.snagflims.model.FeaturedShow;
import com.jsb.snagflims.model.SnagFlims;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ResponseCallback {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter  mRvAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Invoke the REST API
        DataRepo.getInstance(getApplicationContext()).getMovies("snagfilms", this);
    }

    @Override
    public void onSucess(Response<SnagFlims> response) {
        Toast.makeText(this, "response success", Toast.LENGTH_SHORT).show();
        mRvAdapter = new RVAdapter(this, response.body());
        mRecyclerView.setAdapter(mRvAdapter);
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
