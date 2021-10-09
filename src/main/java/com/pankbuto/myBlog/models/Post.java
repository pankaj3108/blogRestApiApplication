package com.pankbuto.myBlog.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Post {
    private int postId;
    private String title;
    private String description;
    private String body;
    private String createdDate;
    private String modifiedDate;
    private String type;
    private List<Comment> comments;

    public Post() {
        comments = new ArrayList<>();
        this.createdDate = LocalDate.now().toString();
    }

    public Post(int postId, String title, String description, String body, String type) {
        this.postId = postId;
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdDate = LocalDate.now().toString();
        this.modifiedDate = null;
        this.type = type;
        this.comments = new ArrayList<>();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", type='" + type + '\'' +
                ", comments=" + comments +
                '}';
    }
}
