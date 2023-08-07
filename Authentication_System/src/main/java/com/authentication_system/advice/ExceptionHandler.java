package com.authentication_system.advice;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.authentication_system.vo.ResponseVo;

/**
 * Exception handling class. Handles various exceptions and provides proper
 * response to the client.
 */
@RestControllerAdvice
public class ExceptionHandler {
	/**
	 * Handles UsernameNotFoundException
	 * 
	 * @param UsernameNotFoundException
	 * @return ResponseVo with proper response
	 */
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
	public ResponseVo dataNotFoundException(UsernameNotFoundException exception) {
		return new ResponseVo(401, "Unauthorized", exception.getMessage());
	}

	/**
	 * Handles SQLIntegrityConstraintViolationException
	 * 
	 * @param SQLIntegrityConstraintViolationException
	 * @return ResponseVo with proper response
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseVo duplicateEntryException(SQLIntegrityConstraintViolationException exception) {
		return new ResponseVo(500, "validation error", exception.getMessage());
	}
}
