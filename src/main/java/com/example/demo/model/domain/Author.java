package com.example.demo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2, message = "Name should be min 2 symbols!")
    private String name;

    @NotNull
    @Size(min = 2, message = "Surname should be min 2 symbols!")
    private String surname;

    private String patronymic;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author(String name, String surname, String patronymic, Set<Book> books) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.books = books;
    }
}
