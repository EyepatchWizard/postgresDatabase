package com.sazzad.postgresDatabase.dao;

import com.sazzad.postgresDatabase.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String s);
}
