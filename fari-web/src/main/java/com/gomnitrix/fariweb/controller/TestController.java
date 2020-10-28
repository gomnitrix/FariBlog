package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id){
        return new SuccessResponse.Builder().addItem("productId", id).build().toJson();
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        return new SuccessResponse.Builder().addItem("orderId", id).build().toJson();
    }
}
