package com.mwahdin.library.service;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import com.mwahdin.library.exception.BookAlreadyExistsException;
import com.mwahdin.library.exception.BookNotFoundException;
import com.mwahdin.library.exception.BookValidationException;
import com.mwahdin.library.model.Book;
import com.mwahdin.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return toBookResponse(bookRepository.save(createBook(bookRequest)));
    }

    @Override
    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(Book -> BookResponse.builder()
                        .name(Book.getName())
                        .price(Book.getPrice())
                        .build()
                );
    }

    @Override
    public List<BookResponse> findByName(String name) {
        return bookRepository.findByTitleContaining(name).stream().map(Book->BookResponse.builder()
                .name(Book.getName())
                .price(Book.getPrice())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public BookResponse updateBook(long id, BookRequest bookRequest) {
       if(bookRequest.getName() == null || bookRequest.getName().trim().isEmpty()){
           throw new BookValidationException("BOOK.NAME.CANNOT.BE.EMPTY");
       }
       if (bookRequest.getPrice() < 0){
          throw new BookValidationException("BOOK.PRICE.CANNOT.BE.NEGATIVE");
       }

       Book book = bookRepository.findById(id).orElseThrow(()-> new BookAlreadyExistsException("NOT.FOUND.BOOK"));
       book.setName(bookRequest.getName());
       book.setPrice(bookRequest.getPrice());
       bookRepository.save(book);
       return toBookResponse (book);
    }

    @Override
    public BookResponse deleteById (long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("BOOK.IS.NOT.FOUND"));
        BookResponse response = toBookResponse(book);
        bookRepository.deleteById(id);
        return response;
    }

    private BookResponse toBookResponse (Book book){
        return BookResponse.builder()
                .name(book.getName())
                .price(book.getPrice())
                .build();
    }

    private Book createBook(BookRequest bookRequest){
       return Book.builder()
                .name(bookRequest.getName())
                .price(bookRequest.getPrice())
                .build();
    }

}
