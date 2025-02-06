package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository {
    @Autowired
    private BookMapper bookMapper;

    public Optional<Book> saveBook(Book book) throws DuplicateKeyException {
        try {
            bookMapper.insertBook(book);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(
                    e.getMessage(),
                    Map.of("bookName", "이미 존재하는 책 제목입니다.")
            );
        }
        return Optional.of(book);
    }

    public Optional<List<Book>> findBookName() {
        List<Book> foundBooks = bookMapper.selectBooksAll();

        if (foundBooks.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(foundBooks);
    }
}
