package com.jsb.sampleapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVDataObjectHolder>{

    @NonNull
    @Override
    public RVAdapter.RVDataObjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.RVDataObjectHolder rvDataObjectHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RVDataObjectHolder extends RecyclerView.ViewHolder{

        public RVDataObjectHolder(View itemView) {
            super(itemView);
        }
    }
}
