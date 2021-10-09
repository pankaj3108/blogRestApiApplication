package com.pankbuto.myBlog;

import com.pankbuto.myBlog.models.Post;
import com.pankbuto.myBlog.models.User;
import com.pankbuto.myBlog.repository.BlogRepo;
import com.pankbuto.myBlog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyBlogApplicationTests {

	@Autowired
	private BlogService blogService;

	@MockBean
	private BlogRepo blogRepo;

	@Test
	public void getAllPostsTest() {
		when(blogRepo.findAllPosts()).thenReturn(Stream
				.of(new Post(20, "test case 1 title", "test case 1 description", "test case 1 body of the post", "TEST"),
				new Post(30, "test case 2 title", "test case 2 description", "test case 2 body of the post", "CASE")).collect(Collectors.toList()));
		assertEquals(2, blogService.findAllPosts().size());
	}

	@Test
	public void getPostByIdTest() {
		Post post = new Post(2, "test case 3 title", "test case 3 description", "test case 3 body of the post", "EXAMPLE");
		when(blogRepo.findPostById(post.getPostId())).thenReturn(post);
		assertEquals(post.getPostId(), blogService.findPostById(post.getPostId()).getPostId());
	}

	@Test
	public void addUserTest() {
		User user = new User(4, "Pankaj", "Sharma", true, "pankaj.shar@gmail.com", "mv colony", "Bayana", "Rajasthan", "India", false, "1998/08/31");
		blogService.register(user);
		verify(blogRepo, times(1)).register(user);
	}

}
