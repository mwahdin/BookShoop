package com.mwahdin.library.service;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import com.mwahdin.library.exception.BookAlreadyExistsException;
import com.mwahdin.library.model.Book;
import com.mwahdin.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponse saveBook(BookRequest bookRequest) {
        Optional<Book> result = bookRepository.findByName(bookRequest.getName());
        if(result.isPresent()){
            throw new BookAlreadyExistsException("BOOK.IS.EXIST");
        }
        return bookToBookResponse(bookRepository.save(createBook(bookRequest)));
    }

    @Override
    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(Book -> BookResponse.builder()
                        .id(Book.getId())
                        .name(Book.getName())
                        .price(Book.getPrice())
                        .build()
                );
    }

    private Book createBook(BookRequest bookRequest){
       return Book.builder()
                .name(bookRequest.getName())
                .price(bookRequest.getPrice())
                .build();
    }

    private BookResponse bookToBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .price(book.getPrice())
                .name(book.getName())
                .build();
    }
}
