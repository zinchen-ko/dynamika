package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AuthorView {
    private String name;
    private String surname;
    private String patronymic;
}
