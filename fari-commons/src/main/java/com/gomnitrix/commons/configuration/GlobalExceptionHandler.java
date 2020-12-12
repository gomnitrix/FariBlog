package com.gomnitrix.commons.configuration;

import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
}
