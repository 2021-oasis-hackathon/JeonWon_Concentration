package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BlogSelectedLocationActivity extends AppCompatActivity {

    private static final String TAG = "BlogSelectedLocationActivity";

    private LinearLayout jbMap, jnMap;
    private RelativeLayout jeonJu, jeongeup, gunsan, buan, gochang, imsil, yeosu, suncheon, wando, mokpo, boseong, haenam;

    private RelativeLayout page;
    private TextView title;

    public static String setLocation = "";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_selected_location);

        title = (TextView) findViewById(R.id.title);
        title.setText(HomeActivity.selectLocation + " 어느 지역의 블로그를 작성하시겠습니까?");

        jbMap = (LinearLayout) findViewById(R.id.jbMap);
        jnMap = (LinearLayout) findViewById(R.id.jnMap);

        jeonJu = (RelativeLayout) findViewById(R.id.jeonJu);
        jeongeup = (RelativeLayout) findViewById(R.id.jeongeup);
        gunsan = (RelativeLayout) findViewById(R.id.gunsan);
        buan = (RelativeLayout) findViewById(R.id.buan);
        gochang = (RelativeLayout) findViewById(R.id.gochang);
        imsil = (RelativeLayout) findViewById(R.id.imsil);

        yeosu = (RelativeLayout) findViewById(R.id.yeosu);
        suncheon = (RelativeLayout) findViewById(R.id.suncheon);
        wando = (RelativeLayout) findViewById(R.id.wando);
        mokpo = (RelativeLayout) findViewById(R.id.mokpo);
        boseong = (RelativeLayout) findViewById(R.id.boseong);
        haenam = (RelativeLayout) findViewById(R.id.haenam);

        if (HomeActivity.selectLocation.equals("전라북도")) {
            jnMap.setVisibility(View.GONE);
            jbMap.setVisibility(View.VISIBLE);
        } else if (HomeActivity.selectLocation.equals("전라남도")) {
            jbMap.setVisibility(View.GONE);
            jnMap.setVisibility(View.VISIBLE);

        } else {
            setLocation = "광주";
            Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
            startActivity(intent);
            finish();
        }

        jeonJu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "전주시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        jeongeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "정읍시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        gunsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "군산시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "부안시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        gochang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "고창시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        imsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "임실시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        yeosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "여수시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        suncheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "순천시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        wando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "완도시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        mokpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "목포시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        boseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "보성시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });

        haenam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocation = "해남시";
                Intent intent = new Intent(BlogSelectedLocationActivity.this, BlogWriteActivity.class);
                startActivity(intent);
                finish();

            }
        });


        page = (RelativeLayout) findViewById(R.id.page);
        page.setEnabled(true);
        page.setClickable(true);
        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

    }
}