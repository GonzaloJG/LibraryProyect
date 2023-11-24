package com.booksApiApplication.booksapi.controllers;

import com.booksApiApplication.booksapi.converters.UserConverter;
import com.booksApiApplication.booksapi.dtos.LoginRequestDTO;
import com.booksApiApplication.booksapi.dtos.LoginResponseDTO;
import com.booksApiApplication.booksapi.dtos.SignupRequestDTO;
import com.booksApiApplication.booksapi.dtos.UserDTO;
import com.booksApiApplication.booksapi.entity.User;
import com.booksApiApplication.booksapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupRequestDTO request){
        User user = userService.createUser(userConverter.signup(request));
        return new ResponseEntity<>(userConverter.fromEntity(user), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }
}
