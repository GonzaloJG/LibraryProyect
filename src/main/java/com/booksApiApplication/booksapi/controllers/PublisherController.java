package com.booksApiApplication.booksapi.controllers;

import com.booksApiApplication.booksapi.converters.PublisherConverter;
import com.booksApiApplication.booksapi.dtos.PublisherDTO;
import com.booksApiApplication.booksapi.entity.Book;
import com.booksApiApplication.booksapi.entity.Publisher;
import com.booksApiApplication.booksapi.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherConverter publisherConverter;

    @GetMapping("/publisher")
    public ResponseEntity<List<PublisherDTO>> findAll(){
        return new ResponseEntity<List<PublisherDTO>>(publisherConverter.fromEntity(publisherService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<PublisherDTO> findById(@PathVariable("publisherId") Long id){
        return new ResponseEntity<PublisherDTO>(publisherConverter.fromEntity(publisherService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/publisher")
    public ResponseEntity<PublisherDTO> create(@RequestBody PublisherDTO publisher){
        return new ResponseEntity<PublisherDTO>(publisherConverter.fromEntity(publisherService.save(publisherConverter.fromDto(publisher))), HttpStatus.OK);
    }

    @PutMapping("/publisher")
    public ResponseEntity<PublisherDTO> update(@RequestBody PublisherDTO publisher){
        return new ResponseEntity<PublisherDTO>(publisherConverter.fromEntity(publisherService.save(publisherConverter.fromDto(publisher))), HttpStatus.OK);
    }

    @DeleteMapping("/publisher/{publisherId}")
    public ResponseEntity<Void> delete(@PathVariable("publisherId") Long id){
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
