package com.booksApiApplication.booksapi.services;

import com.booksApiApplication.booksapi.entity.Author;
import com.booksApiApplication.booksapi.entity.Book;
import com.booksApiApplication.booksapi.entity.Publisher;
import com.booksApiApplication.booksapi.repositories.AuthorRepository;
import com.booksApiApplication.booksapi.repositories.BookRepository;
import com.booksApiApplication.booksapi.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private PublisherRepository publisherRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(Long id){
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
    }

    @Transactional
    public Book save(Book book) {

        List<Author> authors = new ArrayList<>();
        for (Author author: book.getAuthors()){
            if (author.getId() == null){
                authors.add(authorRepo.save(author));
            } else {
                Author existAuthor = authorRepo.findById(author.getId())
                        .orElseThrow(() -> new RuntimeException("Invalid ID Autor"));
                authors.add(existAuthor);
            }
        }

        Publisher publisher;
        if (book.getPublisher().getId() == null){
            publisher = publisherRepo.save(book.getPublisher());
        } else {
            publisher = publisherRepo.findById(book.getPublisher().getId())
                    .orElseThrow(() -> new RuntimeException("Invalid ID Publisher"));
        }

        if (book.getId() == null) {
            book.setAuthors(authors);
            book.setPublisher(publisher);
            return bookRepo.save(book);
        }
        Book updateBook = bookRepo.findById(book.getId())
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
        updateBook.setTitle(book.getTitle());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setAuthors(authors);
        updateBook.setPublisher(publisher);
        return bookRepo.save(updateBook);
    }

    @Transactional
    public void delete(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
        bookRepo.delete(book);
    }
}
