package com.blog.blogging.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.blog.blogging.services.CategoryService;

import com.blog.blogging.payloads.CategoryDto;


public interface CategoryService  {
	CategoryDto createCategory(CategoryDto  catDto);
	CategoryDto updateCategory(CategoryDto  catDto, Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(Integer categoryId);
}
