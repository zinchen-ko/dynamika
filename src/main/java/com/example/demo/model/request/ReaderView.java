package com.example.demo.model.request;

import com.example.demo.model.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ReaderView {
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private Set<Book> books;
}
