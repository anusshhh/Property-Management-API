package com.propertymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propertymanagement.dto.PropertyDTO;
import com.propertymanagement.service.IPropertyService;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

	@Autowired
	private IPropertyService propertyService;

	@GetMapping("/properties")
	public ResponseEntity<List<PropertyDTO>> getProperties() {
		List<PropertyDTO> properties = propertyService.getAllProperties();
		ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(properties, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/properties/user/{userId}")
	public ResponseEntity<List<PropertyDTO>> getPropertiesByUser(@PathVariable long userId) {
		List<PropertyDTO> properties = propertyService.getAllPropertiesByUser(userId);
		ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(properties, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/properties")
	public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
		propertyDTO = propertyService.saveProperty(propertyDTO);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("/properties/{propertyId}")
	public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO,
			@PathVariable Long propertyId) {
		propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
		return responseEntity;
	}

	@PatchMapping("/properties/update-description/{propertyId}")
	public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO,
			@PathVariable Long propertyId) {
		propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/properties/{propertyId}")
	public ResponseEntity<PropertyDTO> deleteProperty(@PathVariable Long propertyId) {
		propertyService.deleteProperty(propertyId);
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return responseEntity;
	}

}
