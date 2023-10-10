package com.blog.blogging.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.payloads.CategoryDto;
import com.blog.blogging.services.CategoryService;



@RestController
@RequestMapping("/api/category")
public class CategoryController {	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createUserController(@RequestBody CategoryDto catDto){
		return new ResponseEntity<>(this.categoryService.createCategory(catDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateUserController(@RequestBody CategoryDto catDto, @PathVariable Integer categoryId)
	{
		return new ResponseEntity<>(this.categoryService.updateCategory(catDto, categoryId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Map<String,String>> deleteCategoryController(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("message", "User deleted successfully");
		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategoryController(){
		return new ResponseEntity<>(this.categoryService.getAllCategory(),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public CategoryDto getOneCategoryController(@PathVariable Integer categoryId) {
		return  this.categoryService.getCategoryById(categoryId);
	}
}
