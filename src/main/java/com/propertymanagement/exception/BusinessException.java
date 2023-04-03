package com.propertymanagement.exception;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
	private List<ErrorModel> errorModels;

	public BusinessException(List<ErrorModel> errorModels) {
		this.errorModels = errorModels;

	}
}
