//package com.gomnitrix.fariauth.controller;
//
//import com.gomnitrix.commons.exception.AuthenFailedException;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@RestController
//public class FilterExceptionController extends BasicErrorController {
//
//    public FilterExceptionController() {
//        super(new DefaultErrorAttributes(), new ErrorProperties());
//    }
//
//    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        String message=(String)request.getAttribute("message");
//        throw new AuthenFailedException(message);
//    }
//}