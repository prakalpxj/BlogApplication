package com.blog.blogappapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogappapis.entities.Category;
import com.blog.blogappapis.entities.Post;
import com.blog.blogappapis.entities.User;

public interface PostRepo  extends JpaRepository<Post,Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	/***
	Alternate method
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByString(@Param ("key") String keyword);
	*/
	
	List<Post> findByTitleContaining(String keyword);    
	/* Hibernate generates there queries on its own.
	  TODO: check more about this.
	 This will write a "LIKE" query in SQL
	 Hibernate: select post0_.post_id as post_id1_1_, post0_.added_date as added_da2_1_, post0_.category_category_id as category6_1_, 
	 post0_.content as content3_1_, post0_.image_name as image_na4_1_, post0_.post_title as post_tit5_1_, post0_.user_id as user_id7_1_ 
	 from post post0_ where post0_.post_title like ? escape ?
	 
	 */
}
