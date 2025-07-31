package com.mwahdin.library.service;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {
    BookResponse saveBook(BookRequest book);
    Page<BookResponse> findAll(Pageable pageable);
    BookResponse findByName(String name);

}
