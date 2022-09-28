package com.demo.controller;

import com.demo.service.ApiService;
import com.google.protobuf.Descriptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/user/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getUser(@PathVariable String id){
        return apiService.getUser(id);
    }

}
