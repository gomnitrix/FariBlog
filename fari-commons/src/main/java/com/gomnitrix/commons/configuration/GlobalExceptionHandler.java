package com.gomnitrix.commons.configuration;

import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.exception.BaseException;
import com.gomnitrix.commons.exception.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> handler(BaseException baseException, HttpServletRequest request) {
        log.info("Exception URI: " + request.getRequestURI());
        log.debug("Exception Trace: " + Arrays.toString(baseException.getStackTrace()));
        String response = new ErrorResponse.Builder(baseException).build().toJson();
        return new ResponseEntity<>(response, new HttpHeaders(), baseException.getError().getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handler(Exception exception, HttpServletRequest request) {
        log.info("Exception URI: " + request.getRequestURI());
        log.debug("Exception Trace: " + Arrays.toString(exception.getStackTrace()));
        String response = new ErrorResponse.Builder(new InternalErrorException()).build().toJson();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
