package com.tour.kuma.global.common.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handlerException(Exception e) {
        ErrorResponse response = ErrorResponse.of(ErrorMessage.TEMPERARY_SERVER_ERROR);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Map> handlerException(MethodArgumentNotValidException e) {

        Map<String, List<ValidErrorDTO>> errors = new HashMap<>();

        List<ValidErrorDTO> list = new ArrayList<>();
        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            ValidErrorDTO err = ValidErrorDTO.builder()
                    .field(error.getField())
                    .message(error.getDefaultMessage())
                    .build();
            list.add(err);
        }
        errors.put("errors", list);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleException (ApiException e) {
        log.debug("ApiException : " + e.getMessage());

        ErrorResponse response = ErrorResponse.of(e.getError());
        response.setMessage(e.getMessage());
        response.setZoneDataTime(ZonedDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}