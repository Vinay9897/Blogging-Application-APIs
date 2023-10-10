package com.blog.blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.config.AppContstants;
import com.blog.blogging.payloads.PostDto;
import com.blog.blogging.payloads.PostResponse;
import com.blog.blogging.services.PostService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
			@PathVariable Integer categoryId) {

		PostDto postCreate = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(postCreate, HttpStatus.CREATED);
	}

	// get by id
	@GetMapping("/user/{userId}/category/{categoryId}/posts/{postId}")
	public ResponseEntity<PostDto> getByIdController(@PathVariable Integer postId) {
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<>(postDto, HttpStatus.OK);
	}

	// get all ids

//	@GetMapping("/user/{userId}/category/{categoryId}/posts")
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPostController(
			@RequestParam(value = "pageNumber", defaultValue = AppContstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppContstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// delete by ids

	@DeleteMapping("/posts/{postId}")
	public void deletByIdControler(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
	}

	// update by id
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updateByIdController(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserController(@PathVariable Long userId) {
		List<PostDto> list = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	// get post by catergory
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategoryController(@PathVariable Integer categoryId) {
		List<PostDto> list = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
		List<PostDto> list = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

}
