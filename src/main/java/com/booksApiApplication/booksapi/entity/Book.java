package com.booksApiApplication.booksapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title", length = 100, nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false, length = 100)
    private String isbn;

    @ManyToMany
    @JoinTable(name = "rel_book_author",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name="publisher_id", nullable = false)
    private Publisher publisher;


}
