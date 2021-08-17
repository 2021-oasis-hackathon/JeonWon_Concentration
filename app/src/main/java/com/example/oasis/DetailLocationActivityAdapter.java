package com.example.oasis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DetailLocationActivityAdapter extends RecyclerView.Adapter<DetailLocationActivityAdapter.MyViewHolder> {

    private List<BlogMain> dataList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, content, likeCount, hashTag;
        private ImageView image;

        public MyViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            content = (TextView) v.findViewById(R.id.content);
            likeCount = (TextView) v.findViewById(R.id.likeCount);
            hashTag = (TextView) v.findViewById(R.id.hashTag);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }

    public DetailLocationActivityAdapter(List<BlogMain> list) {

        dataList = list;
    }

    @Override
    public DetailLocationActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_detail_location_adapter, parent, false);

        DetailLocationActivityAdapter.MyViewHolder vh = new DetailLocationActivityAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DetailLocationActivityAdapter.MyViewHolder holder, int position) {
        holder.hashTag.setText(dataList.get(position).getHashTag());
        holder.title.setText(dataList.get(position).getTitle());
        holder.content.setText(dataList.get(position).getContent());
        holder.likeCount.setText(dataList.get(position).getLike());
        holder.image.setImageBitmap(StringToBitMap(dataList.get(position).getImage()));


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
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
}
