package org.gabriel.biblioteca_digital.book;

import org.gabriel.biblioteca_digital.book.dtos.BookDto;
import org.gabriel.biblioteca_digital.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("/book") @RestController
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/all")
    ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getBook(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getBook(id));
    }

    @GetMapping("/search-by-name/{name}")
    ResponseEntity<Page<Book>> searchBookByName(@PathVariable String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return ResponseEntity.ok(service.searchByName(name, pageable));
    }

    @GetMapping("/search-by-author/{name}")
    ResponseEntity<Page<Book>> searchBookByAuthor(@PathVariable String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return ResponseEntity.ok(service.searchByAuthorsName(name, pageable));
    }

    @PostMapping("/create-book")
    ResponseEntity<String> createBook(@RequestBody BookDto book) {
        service.createBook(book.name(), book.author(), book.cover(), book.isbn10(), book.isbn13());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        try {
            service.deleteBook(id);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<String> updateBook(@PathVariable UUID id, @RequestBody BookDto book) {
        service.updateBook(id, book.name(), book.author(), book.cover(), book.isbn10(), book.isbn13());
        return ResponseEntity.ok().build();
    }

}
