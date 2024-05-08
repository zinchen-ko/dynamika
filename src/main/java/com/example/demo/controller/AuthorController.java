package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.request.AuthorView;
import com.example.demo.model.request.BookView;
import com.example.demo.model.response.AuthorPage;
import com.example.demo.model.response.BookPage;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/authors")
@CrossOrigin
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable long id) {
        try {
            return authorService.getAuthor(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Long deleteAuthor(@PathVariable long id) {
        try {
            return authorService.deleteAuthor(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable long id, @Valid @RequestBody AuthorView authorView) {
        return authorService.updateAuthor(id, authorView);
    }

    @GetMapping()
    public AuthorPage getAuthors(
            @RequestParam(value = "page", required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize
    ) {
        return authorService.getAuthors(page, pageSize);
    }

    @PostMapping()
    public Author addAuthor(@Valid @RequestBody AuthorView authorView) {
        return authorService.addAuthor(authorView);
    }
}