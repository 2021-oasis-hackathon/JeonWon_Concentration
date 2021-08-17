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

public class RecommendListActivityAdapter extends RecyclerView.Adapter<RecommendListActivityAdapter.MyViewHolder> {

    private List<Recommend> dataList;
    static public View.OnClickListener onClick;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        private TextView content;
        private ImageView image;

        public MyViewHolder(View v) {
            super(v);

            rootView = v;
            rootView.setEnabled(true);
            rootView.setClickable(true);
            rootView.setOnClickListener(onClick);
            content = (TextView) v.findViewById(R.id.content);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }

    public RecommendListActivityAdapter(List<Recommend> list, View.OnClickListener onClickListener) {
        dataList = list;
        onClick = onClickListener;
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

        holder.rootView.setTag(position);
        holder.content.setText(dataList.get(position).getContent());
        holder.image.setImageBitmap(StringToBitMap(dataList.get(position).getImage()));


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
        //dataList.size()
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
