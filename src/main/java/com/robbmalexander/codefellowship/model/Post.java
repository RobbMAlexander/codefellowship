package com.robbmalexander.codefellowship.model;

import javax.persistence.*;

public class Post {

    public Post(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String userId;
    String postText;

    @ManyToOne
    ApplicationUser userPosts;

    public Post(String userId, String postText) {
        this.userId = userId;
        this. postText = postText;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public ApplicationUser getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ApplicationUser userPosts) {
        this.userPosts = userPosts;
    }
}
