package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
import java.util.Random;

@Entity
@Data
public class UserAccount {

    @Id
    private String username;
    private String firstName, lastName;
    private int followers, following = 0;
    private String profileDescription;
    private String password;
    public ArrayList<Post> posts = new ArrayList<>();
    public int userPosts = 0;
    private int totalLikes = 0;

    public UserAccount() {
    }

    public UserAccount(String username, String firstName, String lastName, String profileDescription, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.profileDescription = profileDescription;
    }

    public UserAccount(String username, String title) {
        this.username = username;
        this.firstName = "N/A";
        this.lastName = "N/A";
        this.profileDescription = title;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public ArrayList<Post> getPost() {
        return posts;
    }

    public void setPost(Post post) {
        posts.add(post);
        userPosts++;
        totalLikes += Integer.parseInt(post.getNumOfLikes());
    }

    public void setFollowers(int number) {
        this.followers = number;
    }

    public void setFollowing(int num) {
        this.following = num;
    }

    public void setPassword(String pw) {
        password = pw;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfileDescription (String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String toString() {
        return "Username: " + username + ", Profile Description: " + profileDescription + " and Posts: " + posts;
    }
}
