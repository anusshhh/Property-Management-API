package com.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propertymanagement.converter.PropertyConverter;
import com.propertymanagement.dto.PropertyDTO;
import com.propertymanagement.entity.PropertyEntity;
import com.propertymanagement.entity.UserEntity;
import com.propertymanagement.exception.BusinessException;
import com.propertymanagement.exception.ErrorModel;
import com.propertymanagement.repository.PropertyRepository;
import com.propertymanagement.repository.UserRepository;
import com.propertymanagement.service.IPropertyService;

@Service
public class PropertyServiceImpl implements IPropertyService {
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private PropertyConverter propertyConverter;
	@Autowired
	private UserRepository userRepository;

	@Override
	public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
		PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(propertyDTO);
		Optional<UserEntity> userEntityOptional=userRepository.findById(propertyDTO.getUserId());
		if(userEntityOptional.isPresent()) {
			propertyEntity.setUserEntity(userEntityOptional.get());
			propertyEntity = propertyRepository.save(propertyEntity);
			propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
		}
		else {
			List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
			ErrorModel errorModel = new ErrorModel();
			errorModel.setCode("INVALID_USER");
			errorModel.setMessage("User does not exist.");
			errorModelList.add(errorModel);
			throw new BusinessException(errorModelList);
		}
		
		return propertyDTO;
	}

	@Override
	public List<PropertyDTO> getAllProperties() {
		List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAll();
		List<PropertyDTO> propertyDTOlist = new ArrayList<PropertyDTO>();
		for (PropertyEntity pe : propertyEntityList) {
			PropertyDTO pd = propertyConverter.convertEntityToDTO(pe);
			propertyDTOlist.add(pd);
		}
		return propertyDTOlist;
	}

	@Override
	public List<PropertyDTO> getAllPropertiesByUser(Long userId) {
		List<PropertyEntity> propertyEntityList = propertyRepository.findAllByUserEntityId(userId);
		List<PropertyDTO> propertyDTOlist = new ArrayList<PropertyDTO>();
		for (PropertyEntity pe : propertyEntityList) {
			PropertyDTO pd = propertyConverter.convertEntityToDTO(pe);
			propertyDTOlist.add(pd);
		}
		return propertyDTOlist;
	}

	@Override
	public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
		if (optionalEntity.isPresent()) {
			PropertyEntity propertyEntity = optionalEntity.get();
			propertyEntity.setTitle(propertyDTO.getTitle());
			propertyEntity.setPrice(propertyDTO.getPrice());
			propertyEntity.setDescription(propertyDTO.getDescription());
			propertyEntity.setAddress(propertyDTO.getAddress());
			propertyRepository.save(propertyEntity);
			propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
		}
		return propertyDTO;
	}

	@Override
	public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
		Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
		if (optionalEntity.isPresent()) {
			PropertyEntity propertyEntity = optionalEntity.get();
			propertyEntity.setDescription(propertyDTO.getDescription());
			propertyRepository.save(propertyEntity);
			propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
		}
		return propertyDTO;
	}

	@Override
	public void deleteProperty(Long propertyId) {
		propertyRepository.deleteById(propertyId);
	}

}
