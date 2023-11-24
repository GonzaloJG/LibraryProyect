package com.booksApiApplication.booksapi.dtos;

import com.booksApiApplication.booksapi.entity.Book;
import com.booksApiApplication.booksapi.entity.User;
import com.booksApiApplication.booksapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
        private Long id;
        private User user;
        private Book book;
        private LocalDateTime regDate;
        private Status status;
}
