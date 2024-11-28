package com.example.rest_api.repository;

import com.example.rest_api.model.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {

    @Override
    Mono<Book> findById(Long Id);

    @Override
    Mono<Book> save(Book book);

    @Override
    Mono<Void> deleteById(Long id);

    @Override
    Mono<Boolean> existsById(Long id);

    @Override
    Mono<Long> count();

    @Override
    Flux<Book> findAll();
}