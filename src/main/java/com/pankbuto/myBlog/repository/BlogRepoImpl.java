package com.pankbuto.myBlog.repository;

import com.pankbuto.myBlog.models.Comment;
import com.pankbuto.myBlog.models.Post;
import com.pankbuto.myBlog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class BlogRepoImpl extends JdbcDaoSupport implements BlogRepo{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    final String allPosts = "SELECT * FROM post";

    final String postById = "SELECT * FROM post where postId = ?";

    final String insertPost = "INSERT INTO post " +
            "(postId, title, description, body, createDate, modifiedDate, type, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    final String signup = "INSERT INTO user " +
            "(userId, firstName, lastName, gender, email, streetName, city, state, country, premiumUser, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    final String userCnf = "select count(*) from user where userId = ?";

    final String postsByUser = "select * from post where userId = ?";

    final String commentInPost = "INSERT INTO comment " +
            "(commentId, body, createDateTime, userId, postId) VALUES (?, ?, ?, ?, ?)";

    final String commentsByPost = "select * from comment where postId = ?";

    final String delUser = "delete from user where userId = ?";

    final String deleteComments = "delete from comment where postId = ?";

    final String deletePost = "delete from post where postId = ?";

    @Override
    public List<Post> findAllPosts() {
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(allPosts);

        List<Post> result = new ArrayList<>();

        for(Map<String, Object> row : rows) {

            List<Comment> comments = new ArrayList<>();

            allCommentsforPost(comments, getJdbcTemplate().queryForList(commentsByPost, new Object[]{(Integer)row.get("postId")}, new int[]{Types.INTEGER}));

            Post post = new Post();

            post.setPostId((Integer)row.get("postId"));
            post.setTitle((String)row.get("title"));
            post.setDescription((String)row.get("description"));
            post.setBody((String)row.get("body"));
            post.setCreatedDate((String)row.get("createDate"));
            post.setModifiedDate((String)row.get("modifiedDate"));
            post.setType((String)row.get("type"));
            post.setComments(comments);

            result.add(post);

        }

        return result;
    }

    private void allCommentsforPost(List<Comment> comments, List<Map<String, Object>> dbComments) {

        dbComments.forEach(rowMap -> {
            Comment cmt = new Comment();

            cmt.setCommentId((Integer)rowMap.get("commentId"));
            cmt.setText((String)rowMap.get("body"));
            cmt.setUserId((Integer)rowMap.get("userId"));
            cmt.setTime((LocalDateTime) rowMap.get("createDateTime"));

            comments.add(cmt);

        });

    }

    @Override
    public Post findPostById(int id) {
        assert getJdbcTemplate() != null;

        List<Comment> list = new ArrayList<>();

        allCommentsforPost(list, getJdbcTemplate().queryForList(commentsByPost, new Object[]{id}, new int[]{Types.INTEGER}));

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(postById, new Object[]{id}, new int[]{Types.INTEGER});

        Post p = null;

        for(Map<String, Object> mapRow : rows) {
            Post post = new Post();

            post.setPostId((Integer)mapRow.get("postId"));
            post.setTitle((String)mapRow.get("title"));
            post.setDescription((String)mapRow.get("description"));
            post.setBody((String)mapRow.get("body"));
            post.setCreatedDate((String)mapRow.get("createDate"));
            post.setModifiedDate((String)mapRow.get("modifiedDate"));
            post.setType((String)mapRow.get("type"));
            post.setComments(list);

           p = post;
           break;
        }

        return p;
    }

    @Override
    public void addPost(int userId, Post post) {

        Object[] param = new Object[]{ post.getPostId(), post.getTitle(), post.getDescription(), post.getBody(), post.getCreatedDate(), post.getModifiedDate(), post.getType(), userId };

        getJdbcTemplate().update(insertPost, param);
    }

    @Override
    public void register(User user) {

        System.out.println(user);

        getJdbcTemplate().update(signup, new Object[]{
                user.getUserId(), user.getFirstName(), user.getLastName(), user.getGender(), user.getEmail(), user.getStreetName(), user.getCity(), user.getState(), user.getCountry(), user.isPremiumUser(), user.getDateOfBirth()
        });
    }

    @Override
    public int findUserById(int userId) {
        return getJdbcTemplate().queryForObject(userCnf, new Object[] {userId}, Integer.class);
    }

    @Override
    public List<Post> findAllPostsByUserId(int userId) {
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(postsByUser, new Object[]{userId}, new int[]{Types.INTEGER});

        List<Post> posts = new ArrayList<>();

        rows.forEach( rowMap -> {
            Post post = new Post();

            post.setPostId((Integer)rowMap.get("postId"));
            post.setTitle((String)rowMap.get("title"));
            post.setDescription((String)rowMap.get("description"));
            post.setBody((String)rowMap.get("body"));
            post.setCreatedDate((String) rowMap.get("createDate"));
            post.setModifiedDate((String) rowMap.get("modifiedDate"));
            post.setType((String)rowMap.get("type"));

            posts.add(post);
        } );

        return posts;
    }

    @Override
    public List<Comment> getCommentByPost(int postId) {
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(commentsByPost, new Object[]{postId}, new int[]{Types.INTEGER});

        List<Comment> comments = new ArrayList<>();

        rows.forEach(rowMap -> {
            Comment comment = new Comment();

            comment.setCommentId((Integer)rowMap.get("commentId"));
            comment.setText((String)rowMap.get("body"));
            comment.setTime((LocalDateTime)rowMap.get("createDateTime"));
            comment.setUserId((Integer)rowMap.get("userId"));

            comments.add(comment);
        });

        return comments;
    }

    @Override
    public void addCommentInPost(int postId, Comment comment) {

        System.out.println(comment);

        getJdbcTemplate().update(commentInPost, new Object[]{
                comment.getCommentId(), comment.getText(), comment.getTime(), comment.getUserId(), postId
        });
    }

    @Override
    public void deleteUser(int userId) {
        getJdbcTemplate().update(delUser, new Object[]{userId}, new int[]{Types.INTEGER});
    }

    @Override
    public void deleteByPostId(int postId) {
        deleteAllCommentsOfPost(postId);
        getJdbcTemplate().update(deletePost, new Object[]{postId}, new int[]{Types.INTEGER});
    }

    private void deleteAllCommentsOfPost(int postId) {
        getJdbcTemplate().update(deleteComments, new Object[]{postId}, new int[]{Types.INTEGER});
    }
}
