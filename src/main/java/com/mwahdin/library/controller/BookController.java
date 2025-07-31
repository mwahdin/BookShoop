package com.mwahdin.library.controller;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import com.mwahdin.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    @PostMapping
    public ResponseEntity<BookResponse> saveBook(@RequestBody @Valid BookRequest book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll(Pageable pageable){
       return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookResponse> findByName(@PathVariable String name){
        return ResponseEntity.ok(bookService.findByName(name));
    }

}
