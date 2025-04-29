package com.sazzad.postgresDatabase.dao;

import com.sazzad.postgresDatabase.domain.Author;

import java.util.Optional;

public interface AuthorDao {

    void create(Author author);

    Optional<Author> findOne(long l);
}
