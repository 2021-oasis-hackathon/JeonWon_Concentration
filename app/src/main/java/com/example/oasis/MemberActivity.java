package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {

    private static final String TAG = "MemberActivity";
    int REQUEST_EXTERNAL_STORAGE_PERMISSON = 1002;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefUsers = database.getReference("Users");
    private List<User> userList = new ArrayList<>();


    private ImageView profileImage;
    private EditText memberId, memberPw, memberPwCheck, memberNickName;
    private Button idChecked, memberButton;

    private Bitmap bitmap;

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

                String profileString = BitmapToString(bitmap);
                myRefUsers.push().setValue(new User(userId, userPw, userNickName, profileString));

                SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();

                editor.putString("id", userId);
                editor.putString("pw", userPw);
                editor.putString("nickName", userNickName);
                editor.putString("profile", profileString);

                editor.commit();

                Intent intent = new Intent(MemberActivity.this, MainActivity.class);
                intent.putExtra("page", 3);
                startActivity(intent);
                finish();




            }
        });

        profileImage = (ImageView) findViewById(R.id.profileImage);
        profileImage.setEnabled(true);
        profileImage.setClickable(true);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (ContextCompat.checkSelfPermission(MemberActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MemberActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    } else {
                        ActivityCompat.requestPermissions(MemberActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE_PERMISSON);
                    }
                }

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                bitmap = profileImage(data.getData());


                if (bitmap != null) {
                    profileImage.setImageBitmap(bitmap);
                    if (bitmap != null) {

                    }
                }
            } catch (OutOfMemoryError e) {
                return;
            } catch (Exception e) {
                return;
            }
        }

    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

    public String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap profileImage(Uri imgUri){
        String imagePath = getRealPathFromURI(imgUri);
        ExifInterface exif = null;
        try{
            exif = new ExifInterface(imagePath);
        }catch (IOException e){
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Bitmap originaImage = BitmapFactory.decodeFile(imagePath);
        int reduceSize = (int)(originaImage.getHeight()*(1024.0/originaImage.getWidth()));
        Bitmap reduceImage = Bitmap.createScaledBitmap(originaImage, 1024, reduceSize, true);
        Bitmap returnImage = rotate(reduceImage, exifDegree);
        return returnImage;

    }

    public String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(column_index);
    }

    public int exifOrientationToDegrees(int exifOrientation){
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }

    public Bitmap rotate(Bitmap src, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

}