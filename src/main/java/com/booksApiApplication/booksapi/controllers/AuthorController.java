package com.booksApiApplication.booksapi.controllers;

import com.booksApiApplication.booksapi.converters.AuthorConverter;
import com.booksApiApplication.booksapi.dtos.AuthorDTO;
import com.booksApiApplication.booksapi.entity.Author;
import com.booksApiApplication.booksapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorConverter authorConverter;

    @GetMapping(value = "/author")
    public ResponseEntity<List<AuthorDTO>> findAll(){
        return new ResponseEntity<List<AuthorDTO>>(authorConverter.fromEntity(authorService.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "/author/{authorId}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable("authorId") Long id){
        return new ResponseEntity<AuthorDTO>(authorConverter.fromEntity(authorService.findById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/author")
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO author){
        return new ResponseEntity<AuthorDTO>(authorConverter.fromEntity(authorService.save(authorConverter.fromDto(author))), HttpStatus.OK);
    }

    @PutMapping(value = "/author")
    public ResponseEntity<AuthorDTO> update(@RequestBody AuthorDTO author){
        return new ResponseEntity<AuthorDTO>(authorConverter.fromEntity(authorService.save(authorConverter.fromDto(author))), HttpStatus.OK);
    }

    @DeleteMapping(value = "/author/{authorId}")
    public ResponseEntity<Void> delete(@PathVariable("authorId") Long id){
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
