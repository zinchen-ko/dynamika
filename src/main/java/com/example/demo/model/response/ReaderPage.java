package com.example.demo.model.response;

import com.example.demo.model.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReaderPage {

    private List<Reader> readers;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalCount;

}