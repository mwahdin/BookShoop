package com.mwahdin.library.service;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookService {
    BookResponse saveBook(BookRequest book);
    Page<BookResponse> findAll(Pageable pageable);
    List<BookResponse> findByName(String name);
    BookResponse updateBook(Long id, BookRequest bookRequest);
    BookResponse deleteById(long id);
}
