package com.tbaskak.model.book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookUpdateDto {
    
    @NotEmpty
    @NotNull
    private String bookId;
    
    @NotNull
    @Min(0)
    private Integer bookStockCount;


    public BookUpdateDto() {
    }

    public BookUpdateDto(String bookId, Integer bookStockCount) {
        this.bookId = bookId;
        this.bookStockCount = bookStockCount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getBookStockCount() {
        return bookStockCount;
    }

    public void setBookStockCount(Integer bookStockCount) {
        this.bookStockCount = bookStockCount;
    }
}
