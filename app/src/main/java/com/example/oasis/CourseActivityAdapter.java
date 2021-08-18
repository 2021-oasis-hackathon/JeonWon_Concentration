package com.example.oasis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseActivityAdapter extends RecyclerView.Adapter<CourseActivityAdapter.MyViewHolder> {

    private List<Course> dataList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView place;
        private ImageView image;

        public MyViewHolder(View v) {
            super(v);

            place = (TextView) v.findViewById(R.id.place);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }

    public CourseActivityAdapter(List<Course> list) {
        dataList = list;
    }

    @Override
    public CourseActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_course_adapter, parent, false);

        CourseActivityAdapter.MyViewHolder vh = new CourseActivityAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CourseActivityAdapter.MyViewHolder holder, int position) {

        holder.place.setText(dataList.get(position % dataList.size()).getPlace());
        holder.image.setImageBitmap(dataList.get(position % dataList.size()).getBitmap());

    }
    @Override
    public int getItemCount() {
        return dataList == null ? 0 : Integer.MAX_VALUE;
        //dataList.size()
    }
}
