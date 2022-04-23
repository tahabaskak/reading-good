package com.tbaskak.model.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderDto {

    private String id;

    @NotNull
    @NotEmpty
    private String bookId;

    @NotNull
    @NotEmpty
    private String customerId;

    @Min(1)
    private Integer orderCount;

    public OrderDto() {
    }

    public OrderDto(String bookId, String customerId, Integer orderCount) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.orderCount = orderCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
