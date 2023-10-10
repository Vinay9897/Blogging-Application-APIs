package com.blog.blogging.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.blog.blogging.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandling(ResourceNotFoundException ex){
		HashMap<String,String> map = new HashMap<>();
		map.put("message", ex.resourceName + " not found with " + ex.fieldName +" " + ex.fieldValue );
		return new ResponseEntity<>((map),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
}
