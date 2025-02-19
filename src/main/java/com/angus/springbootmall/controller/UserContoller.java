package com.angus.springbootmall.controller;

import com.angus.springbootmall.dto.UserRegisterRequest;
import com.angus.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.angus.springbootmall.model.User;


@RestController
public class UserContoller {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    private ResponseEntity<User> addUser(@RequestBody @Valid UserRegisterRequest registerReq)
    {

        Integer createdId = userService.register(registerReq);
        User user = userService.getUserById(createdId);


        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


}
