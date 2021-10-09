package com.pankbuto.myBlog.models;

import java.time.LocalDateTime;

public class Comment {
    private int commentId;
    private int userId;
    private String text;
    private LocalDateTime time;

    public Comment() {
        this.time = LocalDateTime.now();
    }

    public Comment(int commentId, int userId, String text) {
        this.commentId = commentId;
        this.userId = userId;
        this.text = text;
        this.time = LocalDateTime.now();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
