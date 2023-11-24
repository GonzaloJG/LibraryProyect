package com.booksApiApplication.booksapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name="address_line", nullable = false, length = 100)
    private String addressLine;

    @Column(name="city", length = 100, nullable = false)
    private String city;

    @Column(name="state", nullable = false, length = 100)
    private String state;

    @Column(name="zip", nullable = false, length = 100)
    private String zip;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
