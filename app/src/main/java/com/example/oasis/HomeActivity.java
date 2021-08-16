package com.example.oasis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeActivity extends Fragment {

    private static final String TAG = "HomeActivity";

    private View v;

    private ImageView jMap, jbMap;
    private TextView textView;

    private Button button;

    public HomeActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_home, container, false);

        jMap = (ImageView) v.findViewById(R.id.jMap);
        jbMap = (ImageView) v.findViewById(R.id.jbMap);

        jMap.setEnabled(true);
        jMap.setClickable(true);

        jbMap.setEnabled(true);
        jbMap.setClickable(true);

        textView = (TextView) v.findViewById(R.id.textView);
        textView.setEnabled(true);
        textView.setClickable(true);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                jbMap.setVisibility(View.GONE);
            }
        });

        jMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jbMap.setVisibility(View.VISIBLE);
            }
        });

        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseListActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }

}
