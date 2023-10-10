package com.blog.blogging.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	
	private Long id;
	
	@NotEmpty(message= "Name should not be empty")
	private String name;
	@Email(message="Email must be Valid")
	private String email;
	@NotNull
	@Size(min = 3, max = 10, message="password must be in a range of 3 to 10")
	private String password;
	@NotEmpty(message="About should not be empty")
	private String about;
}
