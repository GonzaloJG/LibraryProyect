package com.booksApiApplication.booksapi.services;

import com.booksApiApplication.booksapi.entity.Author;
import com.booksApiApplication.booksapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;
    public List<Author> findAll(){
        return authorRepo.findAll();
    }

    public Author findById(Long id){
        return authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
    }

    @Transactional
    public Author save(Author author) {
        if (author.getId() == null) {
            return authorRepo.save(author);
        }
        Author updateAuthor = findById(author.getId());
        updateAuthor.setFirstName(author.getFirstName());
        updateAuthor.setLastName(author.getLastName());
        return authorRepo.save(updateAuthor);
    }

    @Transactional
    public void delete (Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));

        authorRepo.delete(author);
    }

}
