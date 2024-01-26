package org.gabriel.biblioteca_digital.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.gabriel.biblioteca_digital.book.Book;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 98778926175L;

    @Id @GeneratedValue @Column(name = "id")
    UUID id;
    String name;
    @JsonIgnore
    String password;
    String email;
    @JsonIgnore
    boolean admin;

    @ManyToMany @JoinTable(name = "users_books", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> books;



    public User(String name, String password, String email, boolean admin) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.books = new HashSet<>();
    }

    public User() {
        new User("", "", "", false);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
