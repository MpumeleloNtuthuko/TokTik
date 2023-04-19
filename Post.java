package com.example.demo.model;

import lombok.Data;

import java.util.Random;

@Data
public class Post {

    private String title, nameOfVideoClip;
    private String numOfLikes, numOfViews;
    private int postId;

    public Post() {
    }

    public Post(String title, String nameOfVideoClip, String numOfLikes, String numOfViews) {
        this.title = title;
        this.nameOfVideoClip = nameOfVideoClip;
        this.numOfLikes = numOfLikes;
        Random fol = new Random();
        this.numOfViews = Integer.toBinaryString(fol.nextInt(100000));
        this.postId = fol.nextInt(938292);
    }

    public Post(String title, String videoFile, String likes) {
        this.title = title;
        this.nameOfVideoClip = videoFile;
        this.numOfLikes = likes;
//        this.numOfViews = numOfViews;
        Random fol = new Random();
        this.postId = fol.nextInt(938292);
    }

    public String getTitle() {
        return title;
    }

    public int getPostId () {
        return this.postId;
    }

    public String getNameOfVideoClip() {
        return nameOfVideoClip;
    }

    public String getNumOfLikes() {
        return numOfLikes;
    }

    public String getNumOfViews() { return numOfViews; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNameOfVideoClip(String nameOfVideoClip) {
        this.nameOfVideoClip = nameOfVideoClip;
    }

    public void setNumOfLikes(String numOfLikes) {
        this.numOfLikes = numOfLikes;
    }
}
