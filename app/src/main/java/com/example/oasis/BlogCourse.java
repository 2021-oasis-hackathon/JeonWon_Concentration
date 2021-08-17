package com.example.oasis;

public class BlogCourse {

    private String image;
    private String content;

    public BlogCourse() {}

    public BlogCourse(String image, String content) {
        this.image = image;
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
