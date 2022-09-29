package com.demo.controller;

import com.demo.dto.request.DeleteUsersRequestDto;
import com.demo.dto.request.SaveUsersRequestDto;
import com.demo.dto.request.UpdateUsersRequestDto;
import com.demo.service.ApiService;
import com.google.protobuf.Descriptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/user/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getUser(@PathVariable String id) {
        return apiService.getUser(id);
    }

    @GetMapping("/users/alike/{namelike}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getAlikeUsers(@PathVariable String namelike) throws InterruptedException {
        return apiService.getAlikeUsers(namelike);
    }

    @PostMapping("/users/")
    public List<Map<Descriptors.FieldDescriptor, Object>> saveUsers(@RequestBody List<SaveUsersRequestDto> userList) throws InterruptedException {
        return apiService.saveUsers(userList);
    }

    @PutMapping("/users/")
    public List<Map<Descriptors.FieldDescriptor, Object>> updateUsers(@RequestBody List<UpdateUsersRequestDto> userList) throws InterruptedException {
        return apiService.updateUsers(userList);
    }

    @DeleteMapping("/users/")
    public List<Map<Descriptors.FieldDescriptor, Object>> deleteUsers(@RequestBody List<DeleteUsersRequestDto> userList) throws InterruptedException {
        return apiService.deleteUsers(userList);
    }
}
