package com.example.oasis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeActivity extends Fragment {

    private static final String TAG = "HomeActivity";

    private View v;

    private ImageView jbMap, jnMap;

    private Button button;

    public HomeActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_home, container, false);





        jbMap = (ImageView) v.findViewById(R.id.jbMap);
        jnMap = (ImageView) v.findViewById(R.id.jnMap);

        jbMap.setEnabled(true);
        jbMap.setClickable(true);

        jnMap.setEnabled(true);
        jnMap.setClickable(true);

        jbMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationListActivity.class);
                startActivity(intent);
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
