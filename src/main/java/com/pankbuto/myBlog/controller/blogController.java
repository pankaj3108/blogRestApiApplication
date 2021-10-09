package com.pankbuto.myBlog.controller;

import com.pankbuto.myBlog.models.Comment;
import com.pankbuto.myBlog.models.Post;
import com.pankbuto.myBlog.models.User;
import com.pankbuto.myBlog.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogApplication")
public class blogController {

    private static final Logger logger = LoggerFactory.getLogger(blogController.class);

    @Autowired
    private BlogService blogService;

    @RequestMapping("/posts")
    public List<Post> getAllPosts() {
        logger.info("getAllPosts method is triggered");
        List<Post> posts = this.blogService.findAllPosts();
        logger.info(String.valueOf(posts));
        return posts;
    }

    @GetMapping("posts/{id}")
    public Post getPostById(@PathVariable int id) {
        logger.info("getPostById method is triggered....");
        Post post = this.blogService.findPostById(id);
        logger.info(String.valueOf(post));

        return post;
    }

    @GetMapping("posts/users/{userId}")
    public List<Post> getAllPostByUserId(@PathVariable int userId) {
        logger.info("getAllPostsByUser method is triggered.....");
        List<Post> posts = this.blogService.getAllPostsByUser(userId);
        logger.info(String.valueOf(posts));

        return posts;
    }

    @PostMapping("/addUser")
    public User register(@RequestBody User user) {

        System.out.println(user);

        this.blogService.register(user);

        return user;
    }

    @PostMapping("/addPost/{userId}")
    public String addPost(@PathVariable int userId, @RequestBody Post post) {

        return this.blogService.addPost(userId, post);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable int postId) {
        return this.blogService.getCommentByPost(postId);
    }

    @PostMapping("/posts/{postId}/comment")
    public Comment addComment(@PathVariable int postId, @RequestBody Comment comment) {
        return this.blogService.addCommentInPost(postId, comment);
    }

    @PutMapping("/users/user/{userid}")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        return this.blogService.updateUser(userId, user);
    }

    @DeleteMapping("/posts/{postId}")
    public Post deletePost(@PathVariable int postId) {
        return this.blogService.deletePostByPostId(postId);
    }
}
