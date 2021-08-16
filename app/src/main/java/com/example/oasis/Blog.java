package com.example.oasis;

public class Blog {
    private String title;
    private String date;
    private String content;
    private String image;

    public Blog() {}

    public Blog(String title, String date, String content, String image) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.image = image;
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
}
