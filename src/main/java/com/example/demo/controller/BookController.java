package com.example.demo.controller;

import com.example.demo.model.domain.Book;
import com.example.demo.model.request.BookView;
import com.example.demo.model.response.BookPage;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/books")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        try {
            return bookService.getBook(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteBook(@PathVariable long id) {
        try {
            return bookService.deleteBook(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable long id, @Valid @RequestBody BookView book) {
        return bookService.updateBook(id, book);
    }

    @GetMapping()
    public BookPage getBooks(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "pageSize", required = false) int pageSize
    ) {
        return bookService.getBooks(page, pageSize);
    }

    @PostMapping()
    public Book addBook(@Valid @RequestBody BookView book) {
        return bookService.addBook(book);
    }
}
