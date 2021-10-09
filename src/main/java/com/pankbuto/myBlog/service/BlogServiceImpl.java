package com.pankbuto.myBlog.service;

import com.pankbuto.myBlog.models.Comment;
import com.pankbuto.myBlog.models.Post;
import com.pankbuto.myBlog.models.User;
import com.pankbuto.myBlog.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public List<Post> findAllPosts() {
        return blogRepo.findAllPosts();
    }

    @Override
    public Post findPostById(int id) {
        return this.blogRepo.findPostById(id);
    }

    //
    @Override
    public String addPost(int userId, Post post) {
        int res = this.blogRepo.findUserById(userId);

        if (res > 0) {
            this.blogRepo.addPost(userId, post);

            return "Post created Successfully";
        } else {
            return "User does not exist";
        }

    }

    //
    @Override
    public void register(User user) {
        this.blogRepo.register(user);
    }

    @Override
    public List<Post> getAllPostsByUser(int userId) {
        return this.blogRepo.findAllPostsByUserId(userId);
    }

    //
    @Override
    public List<Comment> getCommentByPost(int postId) {
        return this.blogRepo.getCommentByPost(postId);
    }

    //
    @Override
    public Comment addCommentInPost(int postId, Comment comment) {
        this.blogRepo.addCommentInPost(postId, comment);

        return comment;
    }

    @Override
    public User updateUser(int userId, User user) {
        boolean delUserSuccess = this.blogRepo.findUserById(userId) > 0 ? true : false;

        if(delUserSuccess) {
            this.blogRepo.deleteUser(userId);
        }

        this.blogRepo.register(user);

        return user;
    }

    @Override
    public Post deletePostByPostId(int postId) {
        Post p = this.blogRepo.findPostById(postId);
        if(p != null) {
            this.blogRepo.deleteByPostId(postId);
        }

        return p;
    }
}
