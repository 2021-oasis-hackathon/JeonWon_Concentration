package com.example.oasis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {

    private static final String TAG = "MemberActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefUsers = database.getReference("Users");
    private List<User> userList = new ArrayList<>();


    private EditText memberId, memberPw, memberPwCheck, memberNickName;
    private Button idChecked, memberButton;

    private boolean idCheckState = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        memberId = (EditText) findViewById(R.id.memberId);
        memberPw = (EditText) findViewById(R.id.memberPw);
        memberPwCheck = (EditText) findViewById(R.id.memberPwCheck);
        memberNickName = (EditText) findViewById(R.id.memberNickName);

        idChecked = (Button) findViewById(R.id.idChecked);
        memberButton = (Button) findViewById(R.id.memberButton);

        idChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = memberId.getText().toString();
                int count = 0;

                if (userId.getBytes().length <= 0 ) {
                    Toast.makeText(MemberActivity.this, "아이디를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                for(User user : userList) {
                    if (user.getId().equals(userId)) {
                        idCheckState = false;
                        count++;
                        Toast.makeText(MemberActivity.this, "'" + userId + "' 이미 사용중인 아이디 입니다", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (count == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                    builder.setTitle("아이디 확인");
                    builder.setMessage("'" + userId + "' 사용 가능한 아이디입니다");
                    builder.setPositiveButton("사용", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            idCheckState = true;
                        }
                    });

                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            idCheckState = false;
                        }
                    });

                    builder.show();
                }
            }
        });

        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = memberId.getText().toString();
                String userPw = memberPw.getText().toString();
                String userPwCheck = memberPwCheck.getText().toString();
                String userNickName = memberNickName.getText().toString();

                if (!idCheckState) {
                    Toast.makeText(MemberActivity.this, "아이디 중복 확인을 먼저 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(userPw.equals(userPwCheck))) {
                    Toast.makeText(MemberActivity.this, "비밀번호가 일치해야 합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userId.getBytes().length <= 0 || userPw.getBytes().length <= 0 || userPwCheck.getBytes().length <= 0 || userNickName.getBytes().length <= 0) {
                    Toast.makeText(MemberActivity.this, "입력사항을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRefUsers.push().setValue(new User(userId, userPw, userNickName));

                SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();

                editor.putString("id", userId);
                editor.putString("pw", userPw);
                editor.putString("nickName", userNickName);

                editor.commit();

                Intent intent = new Intent(MemberActivity.this, MainActivity.class);
                intent.putExtra("page", 3);
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