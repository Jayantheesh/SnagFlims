package com.jsb.snagflims.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsb.snagflims.R;
import com.jsb.snagflims.model.FeaturedShow;
import com.squareup.picasso.Picasso;

public class ChildRVAdapter extends RecyclerView.Adapter<ChildRVAdapter.ChildRVDataHolder>{

    private Activity mContext;
    private FeaturedShow mData;

    public ChildRVAdapter(Activity context, FeaturedShow data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public ChildRVDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_item_view, viewGroup, false);
        return new ChildRVDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRVDataHolder childRVDataHolder, int i) {
        childRVDataHolder.title.setText(mData.getTrayItems().get(i).getTitle());
        childRVDataHolder.rating.setText(mData.getTrayItems().get(i).getParentalRating());
        String imageUrl = mData.getTrayItems().get(i).getBannerImage();
        Log.e("Jay" , imageUrl);
        String newString = "https" + imageUrl.substring(imageUrl.indexOf(":"));
        Log.e("Jay" , newString);
        Picasso.get().load(newString).into(childRVDataHolder.image);
    }

    @Override
    public int getItemCount() {
        return mData.getTrayItems().size();
    }

    class ChildRVDataHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView rating;
        ImageView image;

        public ChildRVDataHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            image = itemView.findViewById(R.id.image);
        }
    }
}
