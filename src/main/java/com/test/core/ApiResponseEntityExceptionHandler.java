package com.test.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;



/**
 * @author govindarajuv
 *
 */
@RestControllerAdvice
@Slf4j
public class ApiResponseEntityExceptionHandler {

	private static final String MALFORM_REQ_BODY_ERROR = "Malformed JSON request body.";
	private static final String MALFORM_REQ_BODY_META_INFO = "Please check input/form body.";
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({NoHandlerFoundException.class})
	//@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,HttpRequestMethodNotSupportedException.class})
	protected ResponseEntity<Object> handleNoHandlerFoundException(Exception ex) {
		log.error("Url Not Found : ", ex);
		List<ApiError> apiErrors = new ArrayList<>();
	    ApiError apError = ApiError.builder().message("Http Method/MIME-Type Violation: ").errorMetaInformation("Url Not Found").build();
	    apiErrors.add(apError);
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().status(HttpStatus.NOT_FOUND).timestamp(LocalDateTime.now()).apiErrors(apiErrors).build();
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler({MethodArgumentNotValidException.class,HttpRequestMethodNotSupportedException.class,HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<ApiErrorResponse> handleHttpMethodOrArgorMediaTypeException(Exception ex) {
		log.error("Bad Request method/Argument : ", ex);
		List<ApiError> apiErrors = new ArrayList<>();
	    ApiError apError = ApiError.builder().message("Http Method/MIME-Type Violation: ").errorMetaInformation("Url Not Found").build();
	    apiErrors.add(apError);
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder().status(HttpStatus.BAD_REQUEST).timestamp(LocalDateTime.now()).apiErrors(apiErrors).build();
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleInternalServerException(Exception ex, WebRequest request) {
		log.error("Internal Error:",ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).timestamp(LocalDateTime.now()).build());
    }
	
}
