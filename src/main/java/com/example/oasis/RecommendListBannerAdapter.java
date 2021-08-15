package com.example.oasis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecommendListBannerAdapter extends RecyclerView.Adapter<RecommendListBannerAdapter.MyViewHolder> {

    private List<String> dataList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView banner;

        public MyViewHolder(View v) {
            super(v);

            banner = (TextView) v.findViewById(R.id.banner);
        }
    }

    public RecommendListBannerAdapter(List<String> list) {
        dataList = list;
    }

    @Override
    public RecommendListBannerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recommend_list_banner_adapter, parent, false);

        RecommendListBannerAdapter.MyViewHolder vh = new RecommendListBannerAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecommendListBannerAdapter.MyViewHolder holder, int position) {

        holder.banner.setText(dataList.get(position % dataList.size()));


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : Integer.MAX_VALUE;
    }
}

