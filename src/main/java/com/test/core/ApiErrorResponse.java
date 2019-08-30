package com.test.core;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author govindarajuv
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class ApiErrorResponse {
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private List<ApiError> apiErrors;

	
	/*
	private ApiError() {
		timestamp = LocalDateTime.now();
	}
	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}
	public ApiError(HttpStatus status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	}
	public static ApiError builder() {
		ApiError apiError = new ApiError();
		apiError.setMessage("Error");
		return apiError;
	}
	*/
	
}
