package com.global.book.dto;

public class PostDto {

    private double id;
    private double userId;
    private String body;
    private String title;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
