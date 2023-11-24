package com.booksApiApplication.booksapi.services;

import com.booksApiApplication.booksapi.entity.Book;
import com.booksApiApplication.booksapi.entity.Register;
import com.booksApiApplication.booksapi.entity.User;
import com.booksApiApplication.booksapi.repositories.BookRepository;
import com.booksApiApplication.booksapi.repositories.RegisterRepository;
import com.booksApiApplication.booksapi.repositories.UserRepository;
import com.booksApiApplication.booksapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BookRepository bookRepo;

    public List<Register> findAll() {
        return registerRepo.findAll();
    }

    public Register findById(Long id){
        return registerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
    }

    @Transactional
    public Register save(Register register) {

        //Para saber el usuario que esta creando la orden
        User user = UserPrincipal.getCurrentUser();
        /////////////////////

        Book book = null;
        if (register.getBook().getId() == null){
            book = bookRepo.save(register.getBook());
        } else {
            book = bookRepo.findById(register.getBook().getId())
                    .orElseThrow(()-> new RuntimeException("Invalid ID"));
        }

        if(register.getId() == null){
            register.setRegDate(LocalDateTime.now());
            register.setUser(user);
            register.setBook(book);
            return registerRepo.save(register);
        }
        Register updateRegister = registerRepo.findById(register.getId())
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
        updateRegister.setUser(user);
        updateRegister.setBook(book);
        updateRegister.setStatus(register.getStatus());
        return registerRepo.save(updateRegister);
    }

    @Transactional
    public void delete (Long id) {
        Register register = registerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid ID"));
        registerRepo.delete(register);
    }
}
