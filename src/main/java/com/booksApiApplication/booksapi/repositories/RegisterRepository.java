package com.booksApiApplication.booksapi.repositories;

import com.booksApiApplication.booksapi.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
}
