package com.propertymanagement.converter;

import org.springframework.stereotype.Component;

import com.propertymanagement.dto.UserDTO;
import com.propertymanagement.entity.UserEntity;
@Component
public class UserConverter {
	public UserEntity userDTOtoEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPhone(dto.getPhone());
		return entity;
	}

	public UserDTO userEntityToDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		return dto;
	}
}
