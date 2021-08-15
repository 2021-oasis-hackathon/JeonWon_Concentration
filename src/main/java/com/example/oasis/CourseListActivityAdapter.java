package com.example.oasis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseListActivityAdapter extends RecyclerView.Adapter<CourseListActivityAdapter.MyViewHolder> {

    private List<Course> mDataset;
    static public View.OnClickListener onClick;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView place, context;
        private ImageView image;
        public MyViewHolder(View v, OnItemClickListener listener) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);
            place = (TextView) v.findViewById(R.id.place);
            context = (TextView) v.findViewById(R.id.context);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CourseListActivityAdapter(List<Course> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public CourseListActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_course_list_adapter, parent, false);


        MyViewHolder vh = new MyViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.image.setImageBitmap(mDataset.get(position).getBitmap());
        holder.place.setText(mDataset.get(position).getPlace());
        holder.context.setText(mDataset.get(position).getContext());
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

}
