package com.example.qlsv.exception;


import com.example.qlsv.model.ApiResponese;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponese> handlingRuntimeException(RuntimeException exception){

        ApiResponese apiResponese = new ApiResponese<>();

        apiResponese.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body((apiResponese));
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponese> handlingAppException(AppException exception){

        ErrorCode errorCode = exception.getErrorCode();

        ApiResponese apiResponese = new ApiResponese<>();

        apiResponese.setCode(errorCode.getCode());
        apiResponese.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body((apiResponese));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponese> hanlingvalidation(MethodArgumentNotValidException exception){

        String enumkey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumkey);
        }catch (IllegalArgumentException e){

        }

        ApiResponese apiResponese = new ApiResponese<>();

        apiResponese.setCode(errorCode.getCode());
        apiResponese.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponese);
    }
}
