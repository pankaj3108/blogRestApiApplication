package com.pankbuto.myBlog.repository;

import com.pankbuto.myBlog.models.Comment;
import com.pankbuto.myBlog.models.Post;
import com.pankbuto.myBlog.models.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

public interface BlogRepo {
    List<Post> findAllPosts();

    Post findPostById(int id);
//
    void addPost(int userId, Post post);
//
    void register(User user);

    int findUserById(int userId);

    List<Post> findAllPostsByUserId(int userId);
//
    List<Comment> getCommentByPost(int postId);
//
    void addCommentInPost(int postId, Comment comment);

    void deleteUser(int userId);

    void deleteByPostId(int postId);
}
