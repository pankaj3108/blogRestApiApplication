package com.pankbuto.myBlog.service;

import com.pankbuto.myBlog.models.Comment;
import com.pankbuto.myBlog.models.Post;
import com.pankbuto.myBlog.models.User;

import java.util.List;

public interface BlogService {

    List<Post> findAllPosts();

    Post findPostById(int id);
//
    String addPost(int userId, Post post);
//
    void register(User user);

    List<Post> getAllPostsByUser(int userId);
//
    List<Comment> getCommentByPost(int postId);
//
    Comment addCommentInPost(int postId, Comment comment);

    User updateUser(int userId, User user);

    Post deletePostByPostId(int postId);
}
