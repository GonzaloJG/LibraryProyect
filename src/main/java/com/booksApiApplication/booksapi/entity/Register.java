package com.booksApiApplication.booksapi.entity;

import com.booksApiApplication.booksapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="registers")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false, updatable = false)
    private User user;

    @OneToOne
    @JoinColumn(name="book_id", nullable = false, updatable = false)
    private Book book;

    @Column(name="regDate", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @Column(name="status", nullable = false)
    private Status status;
}
