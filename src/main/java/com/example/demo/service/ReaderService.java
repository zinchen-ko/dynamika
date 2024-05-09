package com.example.demo.service;

import com.example.demo.model.domain.Book;
import com.example.demo.model.domain.Reader;
import com.example.demo.model.request.ReaderView;
import com.example.demo.model.response.ReaderPage;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.ReaderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ReaderService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final ReaderRepository readerRepository;

    public ReaderService(BookRepository bookRepository, AuthorRepository authorRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.readerRepository = readerRepository;
    }

    public Optional<Reader> getReader(long id) {
        return readerRepository.findById(id);
    }

    public Optional<Long> deleteReader(long id) {
        if (readerRepository.existsById(id)) {
            readerRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }

    public Reader addReader(ReaderView readerView) {
        String name = readerView.getName();
        String surname = readerView.getSurname();
        String patronymic = readerView.getPatronymic();
        Date birthday = readerView.getBirthday();
        Set<Book> books = new HashSet<>();
        Reader reader = new Reader(name, surname, patronymic, birthday, books);
        readerRepository.save(reader);
        return reader;
    }

    public Reader updateReader(long id, ReaderView readerView) {
        Optional<Reader> readerOptional = readerRepository.findById(id);

        Reader reader = readerOptional.get();
        reader.setName(readerView.getName());
        reader.setSurname(readerView.getSurname());
        reader.setPatronymic(readerView.getPatronymic());

        try {
            readerRepository.save(reader);
            return reader;
        } catch (ConstraintViolationException e) {
            throw new RuntimeException(e);
        }
    }

    public ReaderPage getReaders(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return getReaderPage(pageable);
    }

    public ReaderPage getReaderPage(Pageable pageable) {
        Page<Reader> readerPage = readerRepository.findAllPageable(pageable);

        return new ReaderPage(
                readerPage.toList(),
                readerPage.getNumber(),
                readerPage.getSize(),
                readerPage.getTotalPages(),
                readerPage.getTotalElements()
        );
    }
}
