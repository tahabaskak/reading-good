package com.tbaskak.repository;

import com.tbaskak.entity.Book;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {

}
