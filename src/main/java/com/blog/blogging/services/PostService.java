package com.blog.blogging.services;

import java.util.List;

import com.blog.blogging.entity.Post;
import com.blog.blogging.payloads.PostDto;
import com.blog.blogging.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Long userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Long userId);
	List<PostDto> searchPost(String keyword);
}
