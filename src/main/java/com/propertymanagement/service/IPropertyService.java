package com.propertymanagement.service;

import java.util.List;

import com.propertymanagement.dto.PropertyDTO;

public interface IPropertyService {
	public List<PropertyDTO> getAllProperties();
	public List<PropertyDTO> getAllPropertiesByUser(Long userId);
	public PropertyDTO saveProperty(PropertyDTO propertyDTO);
	public PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyId);
	public void deleteProperty(Long propertyId);
	public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId);
}
