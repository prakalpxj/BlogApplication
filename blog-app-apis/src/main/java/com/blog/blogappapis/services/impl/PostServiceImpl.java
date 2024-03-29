package com.blog.blogappapis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blogappapis.entities.Category;
import com.blog.blogappapis.entities.Post;
import com.blog.blogappapis.entities.User;
import com.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.blog.blogappapis.payloads.PostDto;
import com.blog.blogappapis.payloads.PostResponse;
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

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Post createdPost = this.modelMapper.map(postDto, Post.class);
		createdPost.setImageName("default.png");
		createdPost.setAddedDate(new Date());
		createdPost.setCategory(category);
		createdPost.setUser(user);

		Post savedPost = this.postRepo.save(createdPost);

		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		// TODO Auto-generated method stub
		Post postById = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(" Post ", " post id", postId));
		this.postRepo.delete(postById);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(" Post ", " Post id ", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		PostDto updatedPostDto = this.modelMapper.map(updatedPost, PostDto.class);
		return updatedPostDto;
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {
		// TODO Auto-generated method stub
		
		//pagination
		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));    //Sort.by(sortBy).descending()	
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		
		List<PostDto> allPostsDto = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allPostsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalElements());
		postResponse.setLastpage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getOnePost(Integer postId) {
		// TODO Auto-generated method stub
		Post postById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException(" Post ", " Post id ", postId));
		PostDto postDtoById = this.modelMapper.map(postById, PostDto.class);
		return postDtoById;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " category id ", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDtoList = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		System.out.println(postDtoList.get(0).getTitle());
		return postDtoList;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(" User ", " user id ", userId)); // fetching user using
																									// user id
		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtoList = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		System.out.println(postDtoList.get(0).getTitle());
		return postDtoList;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		/*
		 Alternate call in case of custom query
		 List<Post> listPost = this.postRepo.searchByString("%" + keyword + "%");
		 */
		
		List<Post> listPost = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> listPostDto = listPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))     //TODO: learn map, stream api, lambda
				.collect(Collectors.toList());
		
		return listPostDto;
	}

}
