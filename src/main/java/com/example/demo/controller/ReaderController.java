package com.example.demo.controller;

import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.Reader;
import com.example.demo.model.request.BookView;
import com.example.demo.model.request.ReaderView;
import com.example.demo.model.response.BookPage;
import com.example.demo.model.response.ReaderPage;
import com.example.demo.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/readers")
@CrossOrigin
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        try {
            return readerService.getReader(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}")
    public Long deleteReader(@PathVariable long id) {
        try {
            return readerService.deleteReader(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable long id, @Valid @RequestBody ReaderView readerView) {
        return readerService.updateReader(id, readerView);
    }

    @GetMapping()
    public ReaderPage getReaders(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "pageSize", required = false) int pageSize
    ) {
        return readerService.getReaders(page, pageSize);
    }

    @PostMapping()
    public Reader addReader(@Valid @RequestBody ReaderView readerView) {
        return readerService.addReader(readerView);
    }
}

