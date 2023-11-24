package com.booksApiApplication.booksapi.controllers;

import com.booksApiApplication.booksapi.converters.RegisterConverter;
import com.booksApiApplication.booksapi.dtos.RegisterDTO;
import com.booksApiApplication.booksapi.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisterConverter registerConverter;

    @GetMapping(value = "/register")
    public ResponseEntity<List<RegisterDTO>> findAll(){
        return new ResponseEntity<>(registerConverter.fromEntity(registerService.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "register/{registerId}")
    public ResponseEntity<RegisterDTO> findById(@PathVariable("registerId") Long id){
        return new ResponseEntity<>(registerConverter.fromEntity(registerService.findById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterDTO> create (@RequestBody RegisterDTO register) {
        return new ResponseEntity<>(registerConverter.fromEntity(registerService.save(registerConverter.fromDto(register))), HttpStatus.OK);
    }

    @PutMapping(value = "/register")
    public ResponseEntity<RegisterDTO> update(@RequestBody RegisterDTO register) {
        return new ResponseEntity<>(registerConverter.fromEntity(registerService.save(registerConverter.fromDto(register))), HttpStatus.OK);
    }

    @DeleteMapping(value = "/register/{registerId}")
    public ResponseEntity<Void> delete(@PathVariable("registerId") Long id){
        registerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}