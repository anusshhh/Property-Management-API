package com.propertymanagement.converter;

import org.springframework.stereotype.Component;

import com.propertymanagement.dto.PropertyDTO;
import com.propertymanagement.entity.PropertyEntity;

@Component
public class PropertyConverter {

	public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO) {
		PropertyEntity propertyEntity = new PropertyEntity();
		propertyEntity.setTitle(propertyDTO.getTitle());
		propertyEntity.setPrice(propertyDTO.getPrice());
		propertyEntity.setDescription(propertyDTO.getDescription());
		propertyEntity.setAddress(propertyDTO.getAddress());

		return propertyEntity;
	}

	public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity) {
		PropertyDTO propertyDTO = new PropertyDTO();
		propertyDTO.setId(propertyEntity.getId());
		propertyDTO.setTitle(propertyEntity.getTitle());
		propertyDTO.setPrice(propertyEntity.getPrice());
		propertyDTO.setDescription(propertyEntity.getDescription());
		propertyDTO.setAddress(propertyEntity.getAddress());
		propertyDTO.setUserId(propertyEntity.getUserEntity().getId());
		return propertyDTO;
	}

}
