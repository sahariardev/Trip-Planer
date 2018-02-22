package com.sahariar.TripPlanner.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest request)
	{
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(HotelNotFound.class)
	public final ResponseEntity<Object> handleHotelNotFoundException(HotelNotFound ex,WebRequest request)
	{
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),
				request.getDescription(false));
		System.err.println("On htole not found");
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(LocationNotFound.class)
	public final ResponseEntity<Object> handleLocationNotFoundException(LocationNotFound ex,WebRequest request)
	{
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),
				request.getDescription(false));
		System.err.println("Here");
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
	
}
