package com.example.oasis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BlogWriteActivity extends AppCompatActivity {

    public static List<String> keys = MyPageActivity.keys;

    private static final String TAG = "BlogWriteActivity";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    private List<BlogCourse> blogCourseList = new ArrayList<>();
    private String nickName = "";
    private String userProfile = "";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RelativeLayout tagLayout;
    private EditText title, hashTag, content, courseContent;
    private TextView textView, textView2, tagContent, titleText;
    private ImageView courseImage;
    private Button addCourse, addBlog;

    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_write);

        recyclerView = (RecyclerView) findViewById(R.id.blogWriteActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new BlogCourseAdapter(blogCourseList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object obj = v.getTag();
                if(obj != null){
                    final int position = (int) obj;
                    blogCourseList.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.notifyItemRangeChanged(position, blogCourseList.size());

                }
            }
        });
        recyclerView.setAdapter(mAdapter);

        titleText = (TextView) findViewById(R.id.titleText);


        if (HomeActivity.selectLocation.equals("광주")) {
            titleText.setText(HomeActivity.selectLocation + " 블로그");
        } else {
            titleText.setText(HomeActivity.selectLocation + " " + BlogSelectedLocationActivity.setLocation + " 블로그");
        }


        title = (EditText) findViewById(R.id.title);
        hashTag = (EditText) findViewById(R.id.hashTag);
        content = (EditText) findViewById(R.id.content);
        courseContent = (EditText) findViewById(R.id.courseContent);
        textView = (TextView) findViewById(R.id.textView);
        courseImage = (ImageView) findViewById(R.id.courseImage);
        addCourse = (Button) findViewById(R.id.addCourse);
        addBlog = (Button) findViewById(R.id.addBlog);
        tagLayout = (RelativeLayout) findViewById(R.id.tagLayout);
        tagContent = (TextView) findViewById(R.id.tagContent);
        textView2 = (TextView) findViewById(R.id.textView2);

//        hashTag.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//
//                String searchData = hashTag.getText().toString();
//
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    Log.d(TAG, searchData);
//                }
//
//                hashTag.setVisibility(View.GONE);
//                tagLayout.setVisibility(View.VISIBLE);
//                tagContent.setText(searchData);
//                Log.d(TAG, searchData);
//                return true;
//            }
//        });

        courseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = courseContent.getText().toString();
                if (image != null && text.getBytes().length > 0) {
                    BlogCourse blogCourse = new BlogCourse(BitmapToString(image), text);
                    blogCourseList.add(blogCourse);
                    mAdapter.notifyDataSetChanged();
                    image = null;
                    courseImage.setImageBitmap(image);
                    courseContent.setText("");
                    textView.setText("클릭하여 이미지를 추가해 주세요");
                    textView2.setText("");
                }

            }
        });

        addBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTitle = title.getText().toString();
                String strHashTag = hashTag.getText().toString();
                String strContent = content.getText().toString();

                if (strTitle.getBytes().length <= 0 || strContent.getBytes().length <= 0 || strHashTag.getBytes().length <=0 || blogCourseList.size() <= 0) {
                    Toast.makeText(BlogWriteActivity.this, "입력사항을 확인해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
                Calendar time = Calendar.getInstance();

                String formatTime = format.format(time.getTime());



                String key = myRefBlog.push().getKey();
                Log.d(TAG, key);

                String childKey = myRefBlog.child(key).push().getKey();
                String likeKey = myRefBlog.child(key).push().getKey();

                // titleText.setText(HomeActivity.selectLocation + " " + BlogSelectedLocationActivity.setLocation + " 블로그");

                if (HomeActivity.selectLocation.equals("광주")) {
                    myRefBlog.child(key).setValue(
                            new BlogMain(
                                    key, userProfile, strTitle, formatTime, strContent,
                                    blogCourseList.get(0).getImage(), "0", nickName, childKey, likeKey, strHashTag,
                                    HomeActivity.selectLocation, ""));
                } else {
                    myRefBlog.child(key).setValue(
                            new BlogMain(
                                    key, userProfile, strTitle, formatTime, strContent,
                                    blogCourseList.get(0).getImage(), "0", nickName, childKey, likeKey, strHashTag,
                                    HomeActivity.selectLocation, BlogSelectedLocationActivity.setLocation));
                }

                keys.add(key);
                myRefBlog.child(key).child(childKey).setValue(blogCourseList);

                SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
                String nickName = sf.getString("nickName", "");
                myRefBlog.child(key).child(likeKey).push().setValue(nickName);

                /*
                String key = myRefBlog.child("전주시").child("blog").push().getKey();
                Log.d(TAG, key);

                String childKey = myRefBlog.child("전주시").child("blog").child(key).push().getKey();
                String likeKey = myRefBlog.child("전주시").child("blog").child(key).push().getKey();

                myRefBlog.child("전주시").child("blog").child(key).setValue(new BlogMain(key, userProfile, strTitle, formatTime, strContent, blogCourseList.get(0).getImage(), "0", nickName, childKey, likeKey, strHashTag));
                keys.add(key);
                myRefBlog.child("전주시").child("blog").child(key).child(childKey).setValue(blogCourseList);

                SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
                String nickName = sf.getString("nickName", "");
                myRefBlog.child("전주시").child("blog").child(key).child(likeKey).push().setValue(nickName);

                 */

                title.setText("");
                hashTag.setText("");
                content.setText("");
                blogCourseList.clear();
                mAdapter.notifyDataSetChanged();
                finish();



            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sf = getSharedPreferences("user",MODE_PRIVATE);
        nickName = sf.getString("nickName","");
        userProfile = sf.getString("profile", null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                textView.setText("");
                image = getImage(data.getData());
                Log.d(TAG, String.valueOf(image));
                courseImage.setImageBitmap(image);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap getImage(Uri uri){
        String imagePath = getRealPathFromURI(uri);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e){
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