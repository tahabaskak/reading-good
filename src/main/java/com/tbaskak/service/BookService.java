package com.tbaskak.service;

import com.tbaskak.constant.Constant;
import com.tbaskak.exception.CustomException;
import com.tbaskak.entity.Book;
import com.tbaskak.model.book.BookSaveDto;
import com.tbaskak.model.book.BookUpdateDto;
import com.tbaskak.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book save(BookSaveDto model){
        return bookRepository.save(new Book(model.getName(),model.getPrice(), model.getStockCount()));
    }

    public Book update(BookUpdateDto model) throws CustomException {
        Optional<Book> result = bookRepository.findById(model.getBookId());
        if(result.isEmpty()){
            throw new CustomException(Constant.BOOK_NOT_FOUND);
        }
        result.get().setStockCount(model.getBookStockCount());
        bookRepository.save(result.get());
        return result.get();
    }

    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

}
