package com.example.oasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefUsers = database.getReference("Users");
    private List<User> userList = new ArrayList<>();


    private EditText id, pw;
    private Button loginButton, member;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);

        loginButton = (Button) findViewById(R.id.loginButton);
        member = (Button) findViewById(R.id.member);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = id.getText().toString();
                String userPw = pw.getText().toString();

                if (userId.getBytes().length <= 0 || userPw.getBytes().length <= 0) {
                    Toast.makeText(LoginActivity.this, "입력 사항을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for(User user : userList) {
                    if(user.getId().equals(userId) && user.getPw().equals(userPw)) {
                        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sf.edit();

                        editor.putString("id", user.getId());
                        editor.putString("pw", user.getPw());
                        editor.putString("nickName", user.getNickName());

                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("page", 3);
                        startActivity(intent);
                        finish();
                        return;
                    }
                }
            }
        });

        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MemberActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        userList.clear();
        myRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childrenSnapshot: dataSnapshot.getChildren()) {
                    User userData = childrenSnapshot.getValue(User.class);
                    userList.add(userData);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}