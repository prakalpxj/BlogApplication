package com.blog.blogappapis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogappapis.entities.Category;
import com.blog.blogappapis.entities.Post;
import com.blog.blogappapis.entities.User;
import com.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.blog.blogappapis.payloads.PostDto;
import com.blog.blogappapis.repositories.CategoryRepo;
import com.blog.blogappapis.repositories.PostRepo;
import com.blog.blogappapis.repositories.UserRepo;
import com.blog.blogappapis.services.PostService;


@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		// TODO Auto-generated method stub
		
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		User user  = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		Post createdPost = this.modelMapper.map(postDto,Post.class);
		createdPost.setImageName("default.png");
		createdPost.setAddedDate(new Date());
		createdPost.setCategory(category);
		createdPost.setUser(user);
		
		Post savedPost = this.postRepo.save(createdPost);
		
		return this.modelMapper.map(savedPost, PostDto.class) ;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostDto updatePost(PostDto postDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		List<Post> allPosts = this.postRepo.findAll();
		List<PostDto> allPostsDto = allPosts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return allPostsDto;
	}

	@Override
	public PostDto getOnePost(Integer postId) {
		// TODO Auto-generated method stub
		Post postById = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException(" Post ", " Post id " , postId));
		PostDto postDtoById = this.modelMapper.map(postById, PostDto.class);
		return postDtoById;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " category id ", categoryId ));
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtoList = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		System.out.println(postDtoList.get(0).getTitle());
		return postDtoList;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(" User " , " user id ", userId));  //fetching user using user id
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtoList = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		System.out.println(postDtoList.get(0).getTitle());
		return postDtoList;
	}

	@Override
	public PostDto searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
