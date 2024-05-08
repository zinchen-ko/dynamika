package com.example.demo.repo;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsById(Long id);

    Optional<Author> findById(Long id);

    @Query("SELECT c FROM Author c")
    Page<Author> findAllPageable(Pageable pageable);
}
