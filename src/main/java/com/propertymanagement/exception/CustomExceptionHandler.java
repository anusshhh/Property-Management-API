package com.propertymanagement.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException) {
		return new ResponseEntity<List<ErrorModel>>(businessException.getErrorModels(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorModel>> fieldValidation(MethodArgumentNotValidException manv) {
		List<FieldError> fe = manv.getBindingResult().getFieldErrors();
		List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
		ErrorModel errorModel = null;
		for (FieldError fieldError : fe) {
			errorModel = new ErrorModel();
			errorModel.setCode(fieldError.getField());
			errorModel.setMessage(fieldError.getDefaultMessage());
			errorModelList.add(errorModel);
		}
		return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
	}

}
