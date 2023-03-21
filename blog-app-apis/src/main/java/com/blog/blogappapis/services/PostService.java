package com.blog.blogappapis.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.blog.blogappapis.payloads.PostDto;
import com.blog.blogappapis.payloads.PostResponse;

@Service
public interface PostService {

	//create post 
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//delete post 
	void deletePost(Integer postId);
	
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//get all posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);
	
	//get one post
	PostDto getOnePost(Integer postId);
	
	//get post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get post by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
}
