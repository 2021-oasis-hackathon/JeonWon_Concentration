package com.example.oasis;

public class BlogMain {

    private String key;
    private String profile;
    private String title;
    private String date;
    private String content;
    private String image;
    private String like;
    private String nickName;
    private String childKey;

    public BlogMain() {}

    public BlogMain(String key, String profile, String title, String date, String content, String image, String like, String nickName, String childKey) {
        this.profile = profile;
        this.title = title;
        this.date = date;
        this.content = content;
        this.image = image;
        this.like = like;
        this.nickName = nickName;
        this.key = key;
        this.childKey = childKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getChildKey() {
        return childKey;
    }

    public void setChildKey(String childKey) {
        this.childKey = childKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
