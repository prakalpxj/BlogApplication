package com.blog.blogappapis.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	Integer value;
	public ResourceNotFoundException(String resourceName, String fieldName, Integer value) {
		super(String.format("%s not found with %s : %S",resourceName,fieldName,value));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.value = value;
	}
	
	
}
