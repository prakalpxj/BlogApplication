package com.blog.blogappapis.payloads;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min = 4 , message = "Username must be of min 4 characters" )
	private String name;
	
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty
	@Size(min=3, max = 10, message = "Password must be min 3 chars and max 10 chars")
	//can also add regex for matching password
	private String password;
	@NotEmpty
	private String about;
}
