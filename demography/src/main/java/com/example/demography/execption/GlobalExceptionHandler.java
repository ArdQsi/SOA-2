package com.example.demography.execption;

import com.example.demography.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessageDto> catchResourceNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<ErrorMessageDto> catchResourceAlreadyExistsException(Exception e) {
        return new ResponseEntity<>(new ErrorMessageDto(HttpStatus.CONFLICT.value(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ResourceNotAllowedException.class})
    public ResponseEntity<ErrorMessageDto> catchResourceNotAllowedException(Exception e) {
        return new ResponseEntity<>(new ErrorMessageDto(HttpStatus.FORBIDDEN.value(), e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
