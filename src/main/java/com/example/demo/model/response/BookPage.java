package com.example.demo.model.response;

import com.example.demo.model.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookPage {

    private List<Book> books;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalCount;

}
