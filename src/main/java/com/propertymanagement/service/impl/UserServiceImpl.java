package com.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propertymanagement.converter.UserConverter;
import com.propertymanagement.dto.UserDTO;
import com.propertymanagement.entity.UserEntity;
import com.propertymanagement.exception.BusinessException;
import com.propertymanagement.exception.ErrorModel;
import com.propertymanagement.repository.UserRepository;
import com.propertymanagement.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();

	public UserDTO register(UserDTO userDTO) {
		UserEntity userEntity = userConverter.userDTOtoEntity(userDTO);
		Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userEntity.getEmail());
		if (userEntityOptional.isPresent()) {
			List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
			ErrorModel errorModel = new ErrorModel();
			errorModel.setCode("DUPLICATE_USER");
			errorModel.setMessage("User already exists.");
			errorModelList.add(errorModel);
			throw new BusinessException(errorModelList);
		}
		userEntity = userRepository.save(userEntity);
		userDTO = userConverter.userEntityToDTO(userEntity);
		return userDTO;
	}

	@Override
	public UserDTO login(String email, String password) {
		UserDTO userDTO = null;
		Optional<UserEntity> userEntityOptional = userRepository.findByEmailAndPassword(email, password);
		if (userEntityOptional.isPresent()) {
			UserEntity userEntity = userEntityOptional.get();
			userDTO = userConverter.userEntityToDTO(userEntity);
		} else {
			List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
			ErrorModel errorModel = new ErrorModel();
			errorModel.setCode("INVALID_LOGIN");
			errorModel.setMessage("Invalid Email and Password.");
			errorModelList.add(errorModel);
			throw new BusinessException(errorModelList);
		}
		return userDTO;
	}

}
