package com.example.oasis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeActivity extends Fragment {

    private static final String TAG = "HomeActivity";

    private View v;

    private LinearLayout jbMap, jnMap;
    private RelativeLayout jeonJu, jeongeup, gunsan, buan, gochang, imsil, yeosu, suncheon, wando, mokpo, boseong, haenam;


    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    public HomeActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_home, container, false);

        jbMap = (LinearLayout) v.findViewById(R.id.jbMap);
        jnMap = (LinearLayout) v.findViewById(R.id.jnMap);

        jeonJu = (RelativeLayout) v.findViewById(R.id.jeonJu);
        jeongeup = (RelativeLayout) v.findViewById(R.id.jeongeup);
        gunsan = (RelativeLayout) v.findViewById(R.id.gunsan);
        buan = (RelativeLayout) v.findViewById(R.id.buan);
        gochang = (RelativeLayout) v.findViewById(R.id.gochang);
        imsil = (RelativeLayout) v.findViewById(R.id.imsil);

        yeosu = (RelativeLayout) v.findViewById(R.id.yeosu);
        suncheon = (RelativeLayout) v.findViewById(R.id.suncheon);
        wando = (RelativeLayout) v.findViewById(R.id.wando);
        mokpo = (RelativeLayout) v.findViewById(R.id.mokpo);
        boseong = (RelativeLayout) v.findViewById(R.id.boseong);
        haenam = (RelativeLayout) v.findViewById(R.id.haenam);


        arrayList = new ArrayList<>();
        arrayList.add("전라북도");
        arrayList.add("광주");
        arrayList.add("전라남도");
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner = (Spinner)v.findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (arrayList.get(i).equals("전라북도")) {
                    jnMap.setVisibility(View.GONE);
                    jbMap.setVisibility(View.VISIBLE);
                } else if (arrayList.get(i).equals("전라남도")) {
                    jbMap.setVisibility(View.GONE);
                    jnMap.setVisibility(View.VISIBLE);
                } else {
                    jbMap.setVisibility(View.GONE);
                    jbMap.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        jeonJu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "jeonJu", Toast.LENGTH_SHORT).show();
            }
        });

        jeongeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "jeongeup", Toast.LENGTH_SHORT).show();
            }
        });

        gunsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "gunsan", Toast.LENGTH_SHORT).show();
            }
        });

        buan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "buan", Toast.LENGTH_SHORT).show();
            }
        });

        gochang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "gochang", Toast.LENGTH_SHORT).show();
            }
        });

        imsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "imsil", Toast.LENGTH_SHORT).show();
            }
        });

        yeosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "yeosu", Toast.LENGTH_SHORT).show();
            }
        });

        suncheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "suncheon", Toast.LENGTH_SHORT).show();
            }
        });

        wando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "wando", Toast.LENGTH_SHORT).show();
            }
        });

        mokpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "mokpo", Toast.LENGTH_SHORT).show();
            }
        });

        boseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "boseong", Toast.LENGTH_SHORT).show();
            }
        });

        haenam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "haenam", Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }

}
