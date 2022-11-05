package com.blog.blogappapis.services;

import java.util.List;

import com.blog.blogappapis.payloads.CategoryDto;

public interface CategoryService {

	//add
	
	CategoryDto createCategory (CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory (Integer categoryId);
	
	//get one
	CategoryDto getCategory (Integer categoryId);
	
	//get all
	List<CategoryDto> getAllCategories();
}
