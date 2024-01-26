package org.gabriel.biblioteca_digital.book;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity @Table(name = "book")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 364162889528L;
    @Id @GeneratedValue
    UUID id;
    String name;
    String author;
    String cover;

    @Column(name = "isbn-10")
    String isbn10;

    @Column(name = "isbn-13")
    String isbn13;

    public Book(String name, String author, String cover, String isbn10, String isbn13) {
        this.name = name;
        this.author = author;
        this.cover = cover;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
    }


    public Book() {
        new Book("", "", "", "", "");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCover() {
        return cover;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
