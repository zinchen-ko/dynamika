package com.example.demo.service;

import com.example.demo.model.domain.Author;
import com.example.demo.model.domain.Book;
import com.example.demo.model.request.BookView;
import com.example.demo.model.response.BookPage;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.repo.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Book> getBook(long id) {
        return bookRepository.findById(id);
    }

    public Optional<Long> deleteBook(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }

    public Book addBook(BookView bookView) {
        String name = bookView.getName();
        Optional<Author> author;
        if (authorRepository.existsById(bookView.getAuthor().getId()))
            author = authorRepository.findById(bookView.getAuthor().getId());
        else
            author = Optional.of(new Author(
                    bookView.getAuthor().getName(),
                    bookView.getAuthor().getSurname(),
                    bookView.getAuthor().getPatronymic(),
                    bookView.getAuthor().getBooks()
            ));
        String isbn = bookView.getIsbn();
        Book book = new Book(name, author.get(), isbn);
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(long id, BookView bookView) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        Book book = bookOptional.get();
        book.setName(bookView.getName());
        book.setIsbn(bookView.getIsbn());

        try {
            bookRepository.save(book);
            return book;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e);
        }
    }

    public BookPage getBooks(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return getBooksPage(pageable);
    }

    public BookPage getBooksPage(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAllPageable(pageable);

        return new BookPage(
                bookPage.toList(),
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalPages(),
                bookPage.getTotalElements()
        );
    }
}
