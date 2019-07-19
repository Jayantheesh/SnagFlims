package com.jsb.sampleapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVDataObjectHolder>{

    private ITunes mITunes;
    private Context mContext;

    public RVAdapter(ITunes data, Activity context) {
        mITunes = data;
        mContext = context;
    }


    @NonNull
    @Override
    public RVAdapter.RVDataObjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
            return new RVAdapter.RVDataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.RVDataObjectHolder holder, int position) {
        holder.primaryGenreName.setText(mITunes.getResults().get(position).getPrimaryGenreName());
        holder.longDescription.setText(mITunes.getResults().get(position).getLongDescription());
        String imageURL = mITunes.getResults().get(position).getArtworkUrl100();
        Picasso.with(mContext).load(imageURL).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return mITunes.getResultCount();
    }

    public class RVDataObjectHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        ImageView photo;
        TextView primaryGenreName;
        TextView longDescription;

        public RVDataObjectHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            primaryGenreName = itemView.findViewById(R.id.title);
            longDescription = itemView.findViewById(R.id.subject);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
