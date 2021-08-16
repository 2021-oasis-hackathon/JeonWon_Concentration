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

import org.w3c.dom.Text;

public class HomeActivity extends Fragment {

    private static final String TAG = "HomeActivity";

    private View v;

    private ImageView jMap, jbMap;
    private TextView textView, jnMap, gMap;

    private Button button;
    private Button jbButton, jnButton, gButton, jbButton1, jnButton1, gButton1;

    public HomeActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_home, container, false);

        jbButton = (Button) v.findViewById(R.id.jbButton);
        jnButton = (Button) v.findViewById(R.id.jnButton);
        gButton = (Button) v.findViewById(R.id.gButton);


        jbButton1 = (Button) v.findViewById(R.id.jbButton1);
        jnButton1 = (Button) v.findViewById(R.id.jnButton1);
        gButton1 = (Button) v.findViewById(R.id.gButton1);

        jbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
            }
        });

        jnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gMap.setVisibility(View.GONE);
                jbMap.setVisibility(View.GONE);

                jnMap.setVisibility(View.VISIBLE);
            }
        });

        gButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jbMap.setVisibility(View.GONE);
                jnMap.setVisibility(View.GONE);

                gMap.setVisibility(View.VISIBLE);
            }
        });

        jbButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jbButton1.setVisibility(View.GONE);
                jnButton1.setVisibility(View.GONE);
                gButton1.setVisibility(View.GONE);

                jbButton.setVisibility(View.VISIBLE);
                jnButton.setVisibility(View.VISIBLE);
                gButton.setVisibility(View.VISIBLE);

                jnMap.setVisibility(View.GONE);
                gMap.setVisibility(View.GONE);
                jbMap.setVisibility(View.VISIBLE);
            }
        });

        jnButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jbButton1.setVisibility(View.GONE);
                jnButton1.setVisibility(View.GONE);
                gButton1.setVisibility(View.GONE);

                jbButton.setVisibility(View.VISIBLE);
                jnButton.setVisibility(View.VISIBLE);
                gButton.setVisibility(View.VISIBLE);


                gMap.setVisibility(View.GONE);
                jbMap.setVisibility(View.GONE);
                jnMap.setVisibility(View.VISIBLE);
            }
        });

        gButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jbButton1.setVisibility(View.GONE);
                jnButton1.setVisibility(View.GONE);
                gButton1.setVisibility(View.GONE);

                jbButton.setVisibility(View.VISIBLE);
                jnButton.setVisibility(View.VISIBLE);
                gButton.setVisibility(View.VISIBLE);

                jbMap.setVisibility(View.GONE);
                jnMap.setVisibility(View.GONE);
                gMap.setVisibility(View.VISIBLE);
            }
        });

        jMap = (ImageView) v.findViewById(R.id.jMap);
        jbMap = (ImageView) v.findViewById(R.id.jbMap);
        jnMap = (TextView) v.findViewById(R.id.jnMap);
        gMap = (TextView) v.findViewById(R.id.gMap);

        jMap.setEnabled(true);
        jMap.setClickable(true);

        jbMap.setEnabled(true);
        jbMap.setClickable(true);

        jnMap.setEnabled(true);
        jnMap.setClickable(true);

        gMap.setEnabled(true);
        gMap.setClickable(true);

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
