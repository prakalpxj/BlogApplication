package com.blog.blogappapis.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories") // name of the table in database
@NoArgsConstructor
@Getter
@Setter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;

	@Column(length = 1000, nullable = false)
	private String categoryName;
	@Column(name = "description")
	private String categoryDescription;
	
	//The name category is coming from the class (table) which is many in this relationship
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)  // if we remove the parent column, all the child column will automatically be deleted. \
																	//	i.e. if we delete a category, all posts under that category will be deleted.
	private List<Post> posts = new ArrayList<>();
} 
