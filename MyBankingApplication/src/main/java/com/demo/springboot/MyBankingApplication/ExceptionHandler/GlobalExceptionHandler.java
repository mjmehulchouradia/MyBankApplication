package com.demo.springboot.MyBankingApplication.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<String> errors = new ArrayList<>();
		for(ObjectError e : ex.getBindingResult().getAllErrors()) {
			errors.add(e.getDefaultMessage());
		}
		
		//ErrorDto error= new ErrorDto("Invalid Error");
		ErrorDTO error = new ErrorDTO(errors);
		
		ResponseEntity<Object> res = new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
		return res;

	}
	
	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<String> invalidCredentials(BadCredentialsException ex)
	{
		ResponseEntity<String> res = new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		return res;
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	protected ResponseEntity<String> tokenExpired(ExpiredJwtException ex)
	{
		ResponseEntity<String> res=new ResponseEntity<String>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
		
		return res;
	}
	
	
	@ExceptionHandler(Exception.class)
	
		protected ResponseEntity<String> exception(Exception ex)
		{
			ResponseEntity<String> res = new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
			return res;
		}
	
	@ExceptionHandler(BadRequestException.class)
	
	protected ResponseEntity<String> benificiaryNotExist(BadRequestException ex)
	{
		ResponseEntity<String> res=new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
		
		return res;
	}
}

	


