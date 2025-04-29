package com.sazzad.postgresDatabase.dao.impl;

import com.sazzad.postgresDatabase.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl undertest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){

        Author author = Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();

        undertest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L),eq("Abigail Rose"),eq(80)
        );
    }

    @Test
    public void testThatFindOneGenerateCorrectSql(){

        undertest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id name age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }
}
