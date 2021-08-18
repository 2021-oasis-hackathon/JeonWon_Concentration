package com.example.oasis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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

        private ImageView banner;

        public MyViewHolder(View v) {
            super(v);

            banner = (ImageView) v.findViewById(R.id.banner);
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

        holder.banner.setImageBitmap(StringToBitMap(dataList.get(position % dataList.size())));


    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : Integer.MAX_VALUE;
    }
}

