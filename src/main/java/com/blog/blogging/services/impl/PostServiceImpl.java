package com.blog.blogging.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.blogging.entity.Category;
import com.blog.blogging.entity.Post;
import com.blog.blogging.entity.User;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.payloads.PostDto;
import com.blog.blogging.payloads.PostResponse;
import com.blog.blogging.payloads.UserDto;
import com.blog.blogging.repositories.CategoryRepo;
import com.blog.blogging.repositories.PostRepo;
import com.blog.blogging.repositories.UserRepo;
import com.blog.blogging.services.PostService;

import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	UserRepo userRepo;

	@Override
	public PostDto createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
			@PathVariable Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImage("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepo.save(post);
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(post.getContent());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		//
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else if(sortDir.equalsIgnoreCase("dsc")) {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDto = allPosts.stream().map(list -> this.modelMapper.map(list, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalPages());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map(post ->  this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}
