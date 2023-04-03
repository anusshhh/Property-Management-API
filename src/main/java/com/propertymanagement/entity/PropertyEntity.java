package com.propertymanagement.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PROPERTY_TABLE")
public class PropertyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private double price;
	private String description;
	private String address;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID",nullable=false)
	private UserEntity userEntity;
}
