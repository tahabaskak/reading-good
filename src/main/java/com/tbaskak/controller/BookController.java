package com.tbaskak.controller;

import com.tbaskak.model.book.BookSaveDto;
import com.tbaskak.model.book.BookUpdateDto;

import com.tbaskak.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping( "/bookSave")
    public ResponseEntity Save(@Valid @RequestBody BookSaveDto model){
        return new ResponseEntity<>(bookService.save(model), HttpStatus.CREATED);
    }

    @PutMapping("/bookUpdateStock")
    public ResponseEntity Update(@Valid @RequestBody BookUpdateDto model){
            return new ResponseEntity<>( bookService.update(model),HttpStatus.OK);
    }

    @GetMapping("/getBooks")
    public ResponseEntity GetBooks(){
        return new ResponseEntity<>(bookService.allBooks(),HttpStatus.OK);
    }
}
