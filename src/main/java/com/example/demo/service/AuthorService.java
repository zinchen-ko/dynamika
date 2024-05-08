package com.example.demo.service;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.request.AuthorView;
import com.example.demo.model.request.BookView;
import com.example.demo.model.response.AuthorPage;
import com.example.demo.model.response.BookPage;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.repo.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AuthorService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthor(long id) {
        return authorRepository.findById(id);
    }

    public Optional<Long> deleteAuthor(long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }

    public Author addAuthor(AuthorView authorView) {
        String name = authorView.getName();
        String surname = authorView.getSurname();
        String patronymic = authorView.getPatronymic();
        Set<Book> books = new HashSet<>();
        Author author = new Author(name, surname, patronymic, books);
        authorRepository.save(author);
        return author;
    }

    public Author updateAuthor(long id, AuthorView authorView) {
        Optional<Author> bookOptional = authorRepository.findById(id);

        Author author = bookOptional.get();
        author.setName(authorView.getName());
        author.setSurname(authorView.getSurname());
        author.setPatronymic(authorView.getPatronymic());

        try {
            authorRepository.save(author);
            return author;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthorPage getAuthors(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return getAuthorPage(pageable);
    }

    public AuthorPage getAuthorPage(Pageable pageable) {
        Page<Author> authorPage = authorRepository.findAllPageable(pageable);

        return new AuthorPage(
                authorPage.toList(),
                authorPage.getNumber(),
                authorPage.getSize(),
                authorPage.getTotalPages(),
                authorPage.getTotalElements()
        );
    }
}
