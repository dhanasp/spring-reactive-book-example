package com.example.rest_api.controller;


import com.example.rest_api.model.Book;
import com.example.rest_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Book not found with id: " + id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Book> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Long> getCount() {
        return bookService.getAllBooksCount();
    }
}
