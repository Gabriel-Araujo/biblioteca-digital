package org.gabriel.biblioteca_digital.book.service;

import org.gabriel.biblioteca_digital.book.Book;
import org.gabriel.biblioteca_digital.book.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BookService {
    Book createBook(String name, String author, String coverUrl, String isbn10, String isbn13);
    Page<Book> getAll(Pageable pageable);
    Book getBook(UUID id) throws BookNotFoundException;
    Page<Book> searchByName(String name, Pageable pageable);
    Page<Book> searchByAuthorsName(String name, Pageable pageable);

    void deleteBook(UUID id) throws IllegalArgumentException;
    void updateBook(UUID id, String name, String author, String cover, String isbn10, String isbn13) throws BookNotFoundException;
}
