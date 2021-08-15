package com.example.oasis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecommendListActivityAdapter extends RecyclerView.Adapter<RecommendListActivityAdapter.MyViewHolder> {

    private List<Recommend> dataList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView place, likeCount;
        private ImageView image;

        public MyViewHolder(View v) {
            super(v);

            place = (TextView) v.findViewById(R.id.place);
            image = (ImageView) v.findViewById(R.id.image);
            likeCount = (TextView) v.findViewById(R.id.likeCount);
        }
    }

    public RecommendListActivityAdapter(List<Recommend> list) {
        dataList = list;
    }

    @Override
    public RecommendListActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recommend_list_adapter, parent, false);

        RecommendListActivityAdapter.MyViewHolder vh = new RecommendListActivityAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecommendListActivityAdapter.MyViewHolder holder, int position) {

        holder.place.setText(dataList.get(position).getPlace());
        holder.image.setImageBitmap(dataList.get(position).getBitmap());
        holder.likeCount.setText(dataList.get(position).getLikeCount());


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
        //dataList.size()
    }
}
