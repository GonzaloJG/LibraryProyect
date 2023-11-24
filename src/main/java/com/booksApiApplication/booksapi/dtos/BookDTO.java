package com.booksApiApplication.booksapi.dtos;

import com.booksApiApplication.booksapi.entity.Author;
import com.booksApiApplication.booksapi.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private String isbn;
    private List<AuthorDTO> authors;
    private PublisherDTO publisher;

}
