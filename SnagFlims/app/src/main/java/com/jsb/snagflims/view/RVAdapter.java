package com.jsb.snagflims.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsb.snagflims.R;
import com.jsb.snagflims.model.SnagFlims;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVDataHolder>{

    private Activity mContext;
    private SnagFlims mData;

    public RVAdapter(Activity activity, SnagFlims data) {
        mContext = activity;
        mData = data;
    }

    @NonNull
    @Override
    public RVDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new RVDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVDataHolder rvDataHolder, int i) {
        rvDataHolder.trayName.setText(mData.getFeaturedShows().get(i).getTrayName());
        rvDataHolder.viewType.setText(mData.getFeaturedShows().get(i).getViewType());

        RecyclerView mRecyclerView = rvDataHolder.childView;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new ChildRVAdapter(mContext, mData.getFeaturedShows().get(i)));
    }

    @Override
    public int getItemCount() {
        return mData.getFeaturedShows().size();
    }

    class RVDataHolder extends RecyclerView.ViewHolder {

        TextView trayName;
        TextView viewType;
        RecyclerView childView;

        public RVDataHolder(@NonNull View itemView) {
            super(itemView);
            trayName = itemView.findViewById(R.id.trayName);
            viewType = itemView.findViewById(R.id.viewType);
            childView = itemView.findViewById(R.id.childrv);
        }
    }
}
