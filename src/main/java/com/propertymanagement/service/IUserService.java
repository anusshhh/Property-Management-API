package com.propertymanagement.service;

import com.propertymanagement.dto.UserDTO;

public interface IUserService {
	public UserDTO register(UserDTO user);
	public UserDTO login(String email,String password);

}
