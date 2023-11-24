package com.booksApiApplication.booksapi.controllers;

import com.booksApiApplication.booksapi.converters.BookConverter;
import com.booksApiApplication.booksapi.dtos.BookDTO;
import com.booksApiApplication.booksapi.entity.Book;
import com.booksApiApplication.booksapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;

    @GetMapping("/book")
    public ResponseEntity<List<BookDTO>> findAll(){
        return new ResponseEntity<List<BookDTO>>(bookConverter.fromEntity(bookService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDTO> findById(@PathVariable("bookId") Long id){
        return new ResponseEntity<BookDTO>(bookConverter.fromEntity(bookService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO book){
        return new ResponseEntity<BookDTO>(bookConverter.fromEntity(bookService.save(bookConverter.fromDto(book))), HttpStatus.OK);
    }

    @PutMapping("/book")
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO book){
        return new ResponseEntity<BookDTO>(bookConverter.fromEntity(bookService.save(bookConverter.fromDto(book))), HttpStatus.OK);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Void> delete(@PathVariable("bookId") Long id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
