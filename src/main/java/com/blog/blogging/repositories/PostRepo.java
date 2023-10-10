package com.blog.blogging.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogging.entity.Category;
import com.blog.blogging.entity.Post;
import com.blog.blogging.entity.User;


public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByCategory(Category cat);
	List<Post> findByUser(User user);
	List<Post> findByTitleContaining(String title);

}
