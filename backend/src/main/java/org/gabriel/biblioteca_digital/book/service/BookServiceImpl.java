package org.gabriel.biblioteca_digital.book.service;

import org.gabriel.biblioteca_digital.book.Book;
import org.gabriel.biblioteca_digital.book.BookRepository;
import org.gabriel.biblioteca_digital.book.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book createBook(String name, String author, String coverUrl, String isbn10, String isbn13) {
        Book newBook = new Book(name, author, coverUrl, isbn10, isbn13);
        return repository.save(newBook);
    }

    @Override
    public Page<Book> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Book getBook(UUID id) throws BookNotFoundException {
        return repository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Page<Book> searchByName(String name, Pageable pageable) {
        return repository.findAllByName(name, pageable);
    }

    @Override
    public Page<Book> searchByAuthorsName(String name, Pageable pageable) {
        return repository.findAllByAuthor(name, pageable);
    }

    @Override
    public void deleteBook(UUID id) throws IllegalArgumentException {
        repository.deleteById(id);
    }

    @Override
    public void updateBook(UUID id, String name, String author, String cover, String isbn10, String isbn13) throws BookNotFoundException {
        Book targetBook = repository.findById(id).orElseThrow(BookNotFoundException::new);
        targetBook.setName(name.isBlank() ? targetBook.getName() : name);
        targetBook.setAuthor(author.isBlank() ? targetBook.getAuthor() : author);
        targetBook.setCover(cover.isBlank() ? targetBook.getCover() : cover);
        targetBook.setIsbn10(isbn10.isBlank() ? targetBook.getIsbn10() : isbn10);
        targetBook.setIsbn13(isbn13.isBlank() ? targetBook.getIsbn13() : isbn13);
        repository.save(targetBook);
    }
}
