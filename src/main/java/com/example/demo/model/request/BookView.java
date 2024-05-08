package com.example.demo.model.request;

import com.example.demo.model.domain.Author;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class BookView {

    private String name;
    private Author author;
    private String isbn;

}
