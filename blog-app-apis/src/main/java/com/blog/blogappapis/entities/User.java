package com.blog.blogappapis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")   // name of the table in database 
@NoArgsConstructor
@Getter
@Setter

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "user_name", nullable = false, length = 100)    // changing name and giving properties to column.
	private String name;
	private String email;
	private String password;
	private String about;
	
	
		
}