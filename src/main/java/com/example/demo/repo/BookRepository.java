package com.example.demo.repo;

import com.example.demo.model.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT c FROM Book c")
    Page<Book> findAllPageable(Pageable pageable);
}
