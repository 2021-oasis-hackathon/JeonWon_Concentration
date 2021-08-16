package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private HomeActivity homeActivity;
    private RecommendListActivity recommendActivity;
    private BlogListActivity blogActivity;
    private MyPageActivity myPageActivity;

    public static List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()) {
                    case R.id.home:
                        setFrag(0);
                        break;

                    case R.id.recommend:
                        setFrag(1);
                        break;

                    case R.id.blog:
                        setFrag(2);
                        break;

                    case R.id.mypage:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        homeActivity = new HomeActivity();
        recommendActivity = new RecommendListActivity();
        blogActivity = new BlogListActivity();
        myPageActivity = new MyPageActivity();

        Intent intent = getIntent();
        int page = intent.getIntExtra("page", 0);

        setFrag(page);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch(n) {
            case 0:
                ft.replace(R.id.frame, homeActivity);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame, recommendActivity);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.frame, blogActivity);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.frame, myPageActivity);
                ft.commit();
                break;
        }
    }
}