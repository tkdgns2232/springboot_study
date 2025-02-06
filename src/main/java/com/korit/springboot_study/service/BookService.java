package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddBookDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Book;
import com.korit.springboot_study.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public SuccessResponseDto<Book> addBook(ReqAddBookDto reqAddBookDto) throws DuplicateKeyException {
            return new SuccessResponseDto<>(
                    bookRepository
                            .saveBook(new Book(0,reqAddBookDto.getBookName()))
                            .orElseThrow()
            );
    }

}
