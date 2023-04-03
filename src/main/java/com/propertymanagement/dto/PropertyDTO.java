package com.propertymanagement.dto;

import lombok.Data;

@Data
public class PropertyDTO {
	private long id;
	private String title;
	private double price;
	private String description;
	private String address;
	private long userId;
}
