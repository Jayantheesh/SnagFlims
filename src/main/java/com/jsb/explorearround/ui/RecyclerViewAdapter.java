package com.jsb.explorearround.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsb.explorearround.R;

import java.util.ArrayList;

/**
 * Created by JSB on 10/9/15.
 */
public class RecyclerViewAdapter extends RecyclerView
        .Adapter<RecyclerViewAdapter
        .DataObjectHolder> {
    private static String TAG = "RecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private Context mContext;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView name;
        TextView address;
        TextView distance;
        TextView open_status;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            address = (TextView) itemView.findViewById(R.id.textView2);
            distance = (TextView) itemView.findViewById(R.id.textView4);
            open_status = (TextView) itemView.findViewById(R.id.textView3);
            Log.d(TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public RecyclerViewAdapter(ArrayList<DataObject> myDataset, Activity activity) {
        mDataset = myDataset;
        mContext = activity;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getmText1());
        holder.address.setText(mDataset.get(position).getmText2());
        holder.distance.setText(mDataset.get(position).getmText4());
        holder.open_status.setText(mDataset.get(position).getmText3());
        if(mDataset.get(position).getmText3().equalsIgnoreCase("closed")) {
            holder.open_status.setTextColor(Color.RED);
        } else {
            holder.open_status.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_dark));
        }
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
