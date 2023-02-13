package com.blog.blogappapis.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.blog.blogappapis.payloads.PostDto;

import com.blog.blogappapis.services.PostService;


@RestController
@RequestMapping("/api/posts")
@Validated
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createNewPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId ){
		PostDto createdPostDto = this.postService.createPost(postDto, categoryId, userId);
		
		
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
	}
	
	//get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@Valid @PathVariable Integer userId){
		List<PostDto> postDtoList = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@Valid @PathVariable Integer categoryId){
		List<PostDto> postDtoList = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}
	
	
	}


