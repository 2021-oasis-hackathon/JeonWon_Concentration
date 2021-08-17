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

public class BlogListActivityAdapter extends RecyclerView.Adapter<BlogListActivityAdapter.MyViewHolder>{

    private List<BlogMain> dataList;
    static public View.OnClickListener onClick;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        private ImageView profileImage, image;
        private TextView nickName, title, content, date;
        public MyViewHolder(View v) {
            super(v);

            rootView = v;
            rootView.setEnabled(true);
            rootView.setClickable(true);
            rootView.setOnClickListener(onClick);
            profileImage = (ImageView) v.findViewById(R.id.profileImage);
            image = (ImageView) v.findViewById(R.id.image);
            nickName = (TextView) v.findViewById(R.id.nickName);
            title = (TextView) v.findViewById(R.id.title);
            content = (TextView) v.findViewById(R.id.content);
            date = (TextView) v.findViewById(R.id.date);

        }
    }

    public BlogListActivityAdapter(List<BlogMain> getData, View.OnClickListener onClickListener) {
        dataList = getData;
        onClick = onClickListener;
    }


    @Override
    public BlogListActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_blog_list_adapter, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       // holder.date.setText(dataList.get(position).getTime());
       // holder.message.setText(dataList.get(position).getMessage());

        holder.rootView.setTag(position);
        holder.image.setImageBitmap(StringToBitMap(dataList.get(position).getImage()));
        holder.profileImage.setImageBitmap(StringToBitMap(dataList.get(position).getProfile()));
        holder.nickName.setText(dataList.get(position).getNickName());
        holder.title.setText(dataList.get(position).getTitle());
        holder.content.setText(dataList.get(position).getContent());
        holder.date.setText(dataList.get(position).getDate());

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
