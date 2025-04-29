package com.sazzad.postgresDatabase.dao.impl;

import com.sazzad.postgresDatabase.domain.Book;
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
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){

       Book book = Book.builder()
               .isbn("978-1-2345-6789-0")
               .title("The Shadow In The Attic")
               .authorId(1L)
               .build();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, authorId) VALUES (?, ?, ?,)"),
                eq("978-1-2345-6789-0"),
                eq("The Shadow In The Attic"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneGenerateCorrectSql(){

        underTest.findOne("978-1-2345-6789-0");
        verify(jdbcTemplate).query(
                eq("SELECT isbn title authorId FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookMapper>any(),
                eq("978-1-2345-6789-0")
        );
    }

}
