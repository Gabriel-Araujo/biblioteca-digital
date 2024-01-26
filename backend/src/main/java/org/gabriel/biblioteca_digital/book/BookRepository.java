package org.gabriel.biblioteca_digital.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByName(String name);
    Page<Book> findAllByName(String name, Pageable pageable);
    Page<Book> findAllByAuthor(String name, Pageable pageable);
    boolean existsByName(String name);
}
