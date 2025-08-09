package com.mwahdin.library.service.book;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookService {
    BookResponse saveBook(BookRequest book);
    Page<BookResponse> findAll(Pageable pageable);
    List<BookResponse> findByName(String name);
    BookResponse updateBook(long id, BookRequest bookRequest);
    BookResponse deleteById(long id);
    BookResponse findById (long id);
    void softDelete(long id);
}
