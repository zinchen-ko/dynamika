package com.example.demo.model.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reader")
public class Reader {

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

    private Date birthday;

    @OneToMany(mappedBy = "reader")
    private Set<Book> books;

    public Reader(String name, String surname, String patronymic, Date birthday, Set<Book> books) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.books = books;
    }
}
