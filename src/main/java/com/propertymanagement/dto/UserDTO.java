package com.propertymanagement.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	private long id;
	private String name;
	@NotNull(message="Email is mandatory.")
	@NotEmpty(message="Email cannot be empty.")
	private String email;
	private String password;
	private long phone;

}
