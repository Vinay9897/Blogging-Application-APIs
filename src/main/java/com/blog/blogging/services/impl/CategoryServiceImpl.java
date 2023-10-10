package com.blog.blogging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.blogging.entity.Category;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.payloads.CategoryDto;
import com.blog.blogging.repositories.CategoryRepo;
import com.blog.blogging.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
//		Category cat = this.dtoToCategory(catDto);
//		Category saveCategory = this.categoryRepo.save(cat);
//		return this.categoryToDto(saveCategory);
		
//		Category cat = new Category();
//		cat.setCategoryDescription(catDto.getCategoryDescription());
//		cat.setCategoryId(catDto.getCategoryId());
//		cat.setCategoryTitle(catDto.getCategoryTitle());
//		categoryRepo.save(cat);
//		return catDto;
		
		Category cat = this.modelMapper.map(catDto,Category.class);
		Category saveCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(saveCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto catDto, Integer categoryId) {
		Category cat =this.categoryRepo.findById(categoryId).orElseThrow(() -> (new ResourceNotFoundException("Category", "Categorty_id", categoryId)));
		cat.setCategoryId(catDto.getCategoryId());
		cat.setCategoryTitle(catDto.getCategoryTitle());
		cat.setCategoryDescription(catDto.getCategoryDescription());
		Category savedCategory = this.categoryRepo.save(cat);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category_Id", categoryId));
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> cats = this.categoryRepo.findAll();
		List<CategoryDto> catDto = cats.stream().map(cat -> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category  = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category_Id", categoryId));
		this.categoryRepo.delete(category);
	}
	
	public CategoryDto categoryToDto(Category cat) {
		CategoryDto categoryDto  = this.modelMapper.map(cat,CategoryDto.class);
		return categoryDto;
	}
	
	public Category dtoToCategory(CategoryDto catDto)
	{
		Category cat = this.modelMapper.map(catDto,Category.class);
		return cat;
	}

	

}
