package com.example.oasis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private RelativeLayout jeonJu, jeongeup, gunsan, buan, gochang, imsil, yeosu, suncheon, wando, mokpo, boseong, haenam, gwangju;

    public static ArrayList<String> jbLocation = new ArrayList<>();
    public static ArrayList<String> jnLocation = new ArrayList<>();



    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    public static String selectLocation;

    public HomeActivity() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_home, container, false);

        jbLocation.add("전주시");
        jbLocation.add("정읍시");
        jbLocation.add("군산시");
        jbLocation.add("부안시");
        jbLocation.add("고창시");
        jbLocation.add("임실시");

        jnLocation.add("여수시");
        jnLocation.add("순천시");
        jnLocation.add("완도시");
        jnLocation.add("목포시");
        jnLocation.add("보성시");
        jnLocation.add("해남시");

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

        gwangju = (RelativeLayout) v.findViewById(R.id.gwangju);


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
                    gwangju.setVisibility(View.GONE);
                    jbMap.setVisibility(View.VISIBLE);


                    selectLocation = "전라북도";
                } else if (arrayList.get(i).equals("전라남도")) {
                    jbMap.setVisibility(View.GONE);
                    gwangju.setVisibility(View.GONE);
                    jnMap.setVisibility(View.VISIBLE);
                    selectLocation = "전라남도";
                } else {
                    jnMap.setVisibility(View.GONE);
                    jbMap.setVisibility(View.GONE);
                    gwangju.setVisibility(View.VISIBLE);
                    selectLocation = "광주";

                }
                Log.d(TAG, selectLocation);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        gwangju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "광주");
                startActivity(intent);
            }
        });

        jeonJu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "전주시");
                startActivity(intent);
            }
        });

        jeongeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "정읍시");
                startActivity(intent);
            }
        });

        gunsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "군산시");
                startActivity(intent);
            }
        });

        buan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "부안시");
                startActivity(intent);
            }
        });

        gochang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "고창시");
                startActivity(intent);
            }
        });

        imsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "임실시");
                startActivity(intent);
            }
        });

        yeosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "여수시");
                startActivity(intent);
            }
        });

        suncheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "순천시");
                startActivity(intent);
            }
        });

        wando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "완도시");
                startActivity(intent);
            }
        });

        mokpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "목포시");
                startActivity(intent);
            }
        });

        boseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "보성시");
                startActivity(intent);
            }
        });

        haenam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailLocationActivity.class);
                intent.putExtra("location", "해남시");
                startActivity(intent);
            }
        });



        return v;
    }

    public static String location () {
        return selectLocation;
    }

}
