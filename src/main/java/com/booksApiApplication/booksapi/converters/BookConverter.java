package com.booksApiApplication.booksapi.converters;

import com.booksApiApplication.booksapi.dtos.BookDTO;
import com.booksApiApplication.booksapi.entity.Book;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class BookConverter extends AbstractConverter<Book, BookDTO>{
    private AuthorConverter authorConverter;
    private PublisherConverter publisherConverter;
    @Override
    public BookDTO fromEntity(Book entity) {
        return BookDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .isbn(entity.getIsbn())
                .authors(authorConverter.fromEntity(entity.getAuthors()))
                .publisher(publisherConverter.fromEntity(entity.getPublisher()))
                .build();
    }

    @Override
    public Book fromDto(BookDTO dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .authors(authorConverter.fromDto(dto.getAuthors()))
                .publisher(publisherConverter.fromDto(dto.getPublisher()))
                .build();
    }

}
