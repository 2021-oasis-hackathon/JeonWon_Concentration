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

public class BlogCourseAdapter extends RecyclerView.Adapter<BlogCourseAdapter.MyViewHolder> {

    List<BlogCourse> item;
    static public View.OnClickListener onClick;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        private ImageView imageView;
        private ImageView delete_image;
        private TextView content, courseCount;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            delete_image = v.findViewById(R.id.delete_image);
            courseCount = v.findViewById(R.id.courseCount);
            content = v.findViewById(R.id.content);
            delete_image.setClickable(true);
            delete_image.setEnabled(true);
            delete_image.setOnClickListener(onClick);
            rootView = v;
        }
    }

    public BlogCourseAdapter(List<BlogCourse> myDataset, View.OnClickListener onClickListener) {
        item = myDataset;
        onClick = onClickListener;
    }
    @Override
    public BlogCourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_blog_course_adapter, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.delete_image.setTag(position);
        holder.imageView.setImageBitmap(StringToBitMap(item.get(position).getImage()));
        holder.content.setText(item.get(position).getContent());
        holder.courseCount.setText("코스 " + String.valueOf(position + 1));
        //holder.count.setText((position + 1));

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
