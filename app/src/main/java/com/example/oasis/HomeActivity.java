package com.example.oasis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeActivity extends Fragment {

    private static final String TAG = "HomeActivity";

    private View v;

    private ImageView jbMap, jnMap;

    private Button button, gMap;

    public HomeActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_home, container, false);


        jbMap = (ImageView) v.findViewById(R.id.jbMap);
        jnMap = (ImageView) v.findViewById(R.id.jnMap);
        gMap = (Button) v.findViewById(R.id.gMap);

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
        
        jnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "전남클릭!", Toast.LENGTH_SHORT).show();
            }
        });
        
        gMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "광주클릭!", Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }

}
