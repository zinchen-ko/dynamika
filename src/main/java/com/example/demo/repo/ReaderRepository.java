package com.example.demo.repo;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    @Query("SELECT c FROM Reader c")
    Page<Reader> findAllPageable(Pageable pageable);
}
