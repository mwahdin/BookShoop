package com.mwahdin.library.controller;

import com.mwahdin.library.dto.request.BookRequest;
import com.mwahdin.library.dto.response.BookResponse;
import com.mwahdin.library.service.book.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BookResponse>> findByName(@PathVariable String name){
        return ResponseEntity.ok(bookService.findByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest){
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteById (@PathVariable long id){
        return ResponseEntity.ok(bookService.deleteById(id));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(bookService.findById(id));
    }
    @DeleteMapping("/delete/user/{id}")
    public void softDelete(@PathVariable long id){
        bookService.softDelete(id);
    }

}
