package com.example.demo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, message = "Name should be min 2 symbols!")
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @NotNull
    private String isbn;

    public Book(String name, Author author, String isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }
}
