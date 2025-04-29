package com.sazzad.postgresDatabase.dao.impl;

import com.sazzad.postgresDatabase.dao.BookDao;
import com.sazzad.postgresDatabase.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, authorId) VALUES (?, ?, ?,)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String bookIsbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn title authorId FROM books WHERE isbn = ? LIMIT 1",
                new BookMapper(), bookIsbn
        );

        return results.stream().findFirst();
    }

    public static class BookMapper implements RowMapper{
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("authorId"))
                    .build();
        }
    }
}
