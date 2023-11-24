package com.booksApiApplication.booksapi.converters;

import com.booksApiApplication.booksapi.dtos.AuthorDTO;
import com.booksApiApplication.booksapi.dtos.BookDTO;
import com.booksApiApplication.booksapi.entity.Author;
import com.booksApiApplication.booksapi.entity.Book;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;

public class AuthorConverter extends AbstractConverter<Author, AuthorDTO>{

    @Override
    public AuthorDTO fromEntity(Author entity) {
        if (entity == null) return null;

        return AuthorDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

    @Override
    public Author fromDto(AuthorDTO dto) {
        if (dto == null) return null;

        return Author.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }
}
