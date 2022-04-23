package com.tbaskak.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

public class Order {
    @Id
    private String id;

    private String bookId;

    @Field(targetType = DECIMAL128)
    private BigDecimal bookPrice;

    private String customerId;

    private Integer orderCount;

    private Date orderDate;

    private String orderMount;

    private Integer status;

    public Order() {
    }


    public Order(String bookId, String customerId, Integer orderCount, Date orderDate, Integer status,String orderMount,BigDecimal bookPrice) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.orderCount = orderCount;
        this.orderDate = orderDate;
        this.status = status;
        this.orderMount = orderMount;
        this.bookPrice = bookPrice;
    }

    public String getOrderMount() {
        return orderMount;
    }

    public void setOrderMount(String orderMount) {
        this.orderMount = orderMount;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookName) {
        this.bookId = bookName;
    }

    public String getcustomerId() {
        return customerId;
    }

    public void setcustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getorderCount() {
        return orderCount;
    }

    public void setorderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
