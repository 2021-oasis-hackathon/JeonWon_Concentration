package com.example.oasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
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

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefBlog = database.getReference("blog");

    private List<BlogCourse> blogCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap profile1 = BitmapFactory.decodeResource(getResources(), R.drawable.profile1);
        Bitmap profile2 = BitmapFactory.decodeResource(getResources(), R.drawable.profile2);
        Bitmap profile3 = BitmapFactory.decodeResource(getResources(), R.drawable.profile3);
        Bitmap profile4 = BitmapFactory.decodeResource(getResources(), R.drawable.profile4);
























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


    public String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[]  b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}

/*





        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image10);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.image11);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.image12);


        BlogCourse blogCourse = new BlogCourse(BitmapToString(bitmap1), "카페 노아");
        BlogCourse blogCourse2 = new BlogCourse(BitmapToString(bitmap2), "다양한 디저트들");
        BlogCourse blogCourse3 = new BlogCourse(BitmapToString(bitmap3), "소담한 조명");
        blogCourseList.add(blogCourse);
        blogCourseList.add(blogCourse2);
        blogCourseList.add(blogCourse3);

        String key = myRefBlog.push().getKey();
        String childKey = myRefBlog.child(key).push().getKey();
        String likeKey = myRefBlog.child(key).push().getKey();



        myRefBlog.child(key).setValue(
                new BlogMain(
                        key, BitmapToString(profile1), "전주 ‘카페노아’ 커피 맛 좋아!", "2021-08-17", "친구랑 순대국밥을 먹고 헤어지기 아쉬워 차 한잔을 위해 찾아왔다. 외관의 이끌려 카페노아에 와버렸다! 다양한 디저트들도 보였고 특히 케익 류가 많은 것을 볼 수 있었다. 케익을 좋아한다면 꼭 오시길..^^",
                        blogCourseList.get(0).getImage(), "0", "령은", childKey, likeKey, "#카페",
                        "전라북도", "전주시"));


        myRefBlog.child(key).child(childKey).setValue(blogCourseList);

        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
        String nickName = sf.getString("nickName", "");
        myRefBlog.child(key).child(likeKey).push().setValue(nickName);
 Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image01);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.image02);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.image03);


        BlogCourse blogCourse = new BlogCourse(BitmapToString(bitmap1), "카페 비오브");
        BlogCourse blogCourse2 = new BlogCourse(BitmapToString(bitmap2), "디오니 카페");
        BlogCourse blogCourse3 = new BlogCourse(BitmapToString(bitmap3), "시즈너 카페");
        blogCourseList.add(blogCourse);
        blogCourseList.add(blogCourse2);
        blogCourseList.add(blogCourse3);

        String key = myRefBlog.push().getKey();
        String childKey = myRefBlog.child(key).push().getKey();
        String likeKey = myRefBlog.child(key).push().getKey();



        myRefBlog.child(key).setValue(
                new BlogMain(
                        key, BitmapToString(profile1), "전주 데이트 코스 달달한 디저트", "2021-08-18", "오랜만에 지인 분들과 함께 전주 데이트코스를 한번 가보는 게 어떨까 싶었어요.\n" +
                        "고민을 하다가 어딜 갈까 싶다가 최근에 알게 된 곳을 찾아 가 보기로 하였죠.\n" +
                        "전주 데이트코스로 좋은 것이 번화가에 있으면서도 편안하게 쉴 수 있는 공간을 가지고 있어요. 그 뿐만 아니라 연인분들끼리 방문을 하시면 포토존 또한 많기 때문에 사진을 많이 찍으실 수가 있어요.",
                        blogCourseList.get(0).getImage(), "0", "령은", childKey, likeKey, "#데이트",
                        "전라북도", "전주시"));


        myRefBlog.child(key).child(childKey).setValue(blogCourseList);

        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
        String nickName = sf.getString("nickName", "");
        myRefBlog.child(key).child(likeKey).push().setValue(nickName);




                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image04);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.image05);
       // Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.image03);


        BlogCourse blogCourse = new BlogCourse(BitmapToString(bitmap1), "카페 비오브");
        BlogCourse blogCourse2 = new BlogCourse(BitmapToString(bitmap2), "전주 덕진 공원");
       // BlogCourse blogCourse3 = new BlogCourse(BitmapToString(bitmap3), "시즈너 카페");
        blogCourseList.add(blogCourse);
        blogCourseList.add(blogCourse2);
      //  blogCourseList.add(blogCourse3);

        String key = myRefBlog.push().getKey();
        String childKey = myRefBlog.child(key).push().getKey();
        String likeKey = myRefBlog.child(key).push().getKey();



        myRefBlog.child(key).setValue(
                new BlogMain(
                        key, BitmapToString(profile2), "전주 데이트코스", "2021-08-01", "요즘따라 제가 마카롱에 꽂혀서 언제 한번 먹어야지 생각을 했는데 다행히도 전주 데이트코스에 있어서 주문을 하게 되었어요. 두 가지 모두 다른 맛으로 주문을 해서 다행이였고 하나씩 먹으면 살살 녹을 거 같은 느낌이 들어요.\n" +
                        "달달한 카페 후 전주 덕진 공원은 어떠세요?\n" +
                        "야경이 예쁜 전주 여행 소개 시간으로 전주 덕진공원 이야기를 들려드릴게요. 무엇보다 최근 새 단장된 모습이라 많은 전주 공원 중에서 단연 힐링 여행 즐기기에도 안성맞춤이랍니다. 그 멋진 데이트 코스 공유할게요.",
                        blogCourseList.get(0).getImage(), "0", "현성", childKey, likeKey, "#데이트",
                        "전라북도", "전주시"));


        myRefBlog.child(key).child(childKey).setValue(blogCourseList);

        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
        String nickName = sf.getString("nickName", "");
        myRefBlog.child(key).child(likeKey).push().setValue(nickName);



        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image06);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.image07);
       // Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.image03);


        BlogCourse blogCourse = new BlogCourse(BitmapToString(bitmap1), "시즈너 카페");
        BlogCourse blogCourse2 = new BlogCourse(BitmapToString(bitmap2), "디오니 카페");
       // BlogCourse blogCourse3 = new BlogCourse(BitmapToString(bitmap3), "시즈너 카페");
        blogCourseList.add(blogCourse);
        blogCourseList.add(blogCourse2);
      //  blogCourseList.add(blogCourse3);

        String key = myRefBlog.push().getKey();
        String childKey = myRefBlog.child(key).push().getKey();
        String likeKey = myRefBlog.child(key).push().getKey();



        myRefBlog.child(key).setValue(
                new BlogMain(
                        key, BitmapToString(profile3), "1. 밥 카페 정형적인 데이트", "2021-08-03", "싱싱하고 맛있는 제철 음식으로 요리를 한다고 입소문이 자자한 전주 덕진구, 만성동 맛집 시즈너.\n" +
                        "시즈너는 요리사 출신 대표님께서 운영하는 이탈리안 레스토랑으로 전주 만성지구에 위치 하고 있도 메뉴 하나하나 정성스레 요리하여 대접하는 고급 레스토랑이라 이미 만성동 주민들에게는 인정 받은 찐 맛집이다. 전주 디오니카페는 전주 드라이브시, 전주 데이트 시 방문하기에 좋은 전주 대형 카페로 깔끔한 인테리어와 예쁘게 꾸며져 있는 외부로 다양한 인생샷을 함께 찍어갈 수 있어 전주 방문 시 꼭 한 번쯤은 들리기에 좋은 전주 카페이지요." +
                        "야경이 예쁜 전주 여행 소개 시간으로 전주 덕진공원 이야기를 들려드릴게요. 무엇보다 최근 새 단장된 모습이라 많은 전주 공원 중에서 단연 힐링 여행 즐기기에도 안성맞춤이랍니다. 그 멋진 데이트 코스 공유할게요.",
                        blogCourseList.get(0).getImage(), "0", "세현", childKey, likeKey, "#맛집",
                        "전라북도", "전주시"));


        myRefBlog.child(key).child(childKey).setValue(blogCourseList);

        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
        String nickName = sf.getString("nickName", "");
        myRefBlog.child(key).child(likeKey).push().setValue(nickName);


                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.image08);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.image09);
       // Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.image03);


        BlogCourse blogCourse = new BlogCourse(BitmapToString(bitmap1), "노마딕");
        BlogCourse blogCourse2 = new BlogCourse(BitmapToString(bitmap2), "진수곱창");
       // BlogCourse blogCourse3 = new BlogCourse(BitmapToString(bitmap3), "시즈너 카페");
        blogCourseList.add(blogCourse);
        blogCourseList.add(blogCourse2);
      //  blogCourseList.add(blogCourse3);

        String key = myRefBlog.push().getKey();
        String childKey = myRefBlog.child(key).push().getKey();
        String likeKey = myRefBlog.child(key).push().getKey();



        myRefBlog.child(key).setValue(
                new BlogMain(
                        key, BitmapToString(profile4), "전주 맛집코스 추천!", "2021-08-15", "나혼산 화사의 곱창대란으로 핫했던 2017년으로부터 어언 5년이 지났지만 곱창은 젊은 세대들의 입맛도 완전 포섭해서 지금도 인기 많은 메뉴 중 하나로 완전 자리잡았다. 전주 진수곱창도 여전히 인기많은 객리단길 맛집중의 한 곳.\n" +
                        "\n" +
                        "진수성찬도 인기가 많은 메뉴지만 곱창 대창 골라먹는 재미가 있다\n" +
                        "우리가 좋아하는 메뉴라서 더 맛있게 먹을 수 있었다",
                        blogCourseList.get(0).getImage(), "0", "태원", childKey, likeKey, "#맛집",
                        "전라북도", "전주시"));


        myRefBlog.child(key).child(childKey).setValue(blogCourseList);

        SharedPreferences sf = getSharedPreferences("user", MODE_PRIVATE);
        String nickName = sf.getString("nickName", "");
        myRefBlog.child(key).child(likeKey).push().setValue(nickName);


 */