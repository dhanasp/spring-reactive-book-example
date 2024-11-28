package com.example.rest_api.service;

import com.example.rest_api.model.Book;
import com.example.rest_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Service
public class BookService {

    Logger logger = Logger.getLogger("bookService");
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> getAllBooks() {
        logger.info("Getting all books...");
        return bookRepository.findAll().switchIfEmpty(Flux.error(new RuntimeException("No books found")));
    }

    public Mono<Book> getBookById(Long id) {
        return bookRepository.findById(id)
                .doOnSuccess(book -> logger.info("Book found with id: " + id));
    }

    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book)
                .onErrorMap(error -> {
                    logger.warning("Error saving book: " + error.getMessage() + " " + error.getCause());
                    return new RuntimeException(error);
                })
                .doOnSuccess(savedBook -> logger.info("Book saved successfully"));
    }

    public Mono<Void> deleteBook(Long id) {
        return bookRepository.existsById(id)
                .filter(result -> result)
                .map(result -> {
                    logger.info("Book deleted successfully");
                    return result;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Book not found with id: " + id)))
                .flatMap(result -> bookRepository.deleteById(id));
    }

    public Mono<Long> getAllBooksCount() {
        return bookRepository.count();
    }
}
