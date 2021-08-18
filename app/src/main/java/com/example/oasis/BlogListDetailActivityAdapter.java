package com.example.oasis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogListDetailActivityAdapter extends RecyclerView.Adapter<BlogListDetailActivityAdapter.MyViewHolder> {

    List<BlogCourse> item;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        private ImageView imageView;
        private TextView content;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            content = v.findViewById(R.id.content);
            rootView = v;
        }
    }

    public BlogListDetailActivityAdapter(List<BlogCourse> myDataset) {
        item = myDataset;

    }
    @Override
    public BlogListDetailActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_blog_list_detail_adapter, parent, false);

        BlogListDetailActivityAdapter.MyViewHolder vh = new BlogListDetailActivityAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BlogListDetailActivityAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageBitmap(StringToBitMap(item.get(position).getImage()));
        holder.content.setText(item.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return item == null ? 0 : item.size();
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
