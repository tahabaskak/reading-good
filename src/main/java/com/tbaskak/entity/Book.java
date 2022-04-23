package com.tbaskak.entity;

import com.tbaskak.constant.Constant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

public class Book {
    @Id
    private String id;
    @NotNull
    @NotEmpty
    @Size(min = Constant.MIN_LENGTH,max = Constant.MAX_LENGTH)
    private String name;
    @NotNull
    @Min(1)
    @Field(targetType = DECIMAL128)
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Integer stockCount;

    public Book() {
    }

    public Book(String name, BigDecimal price, Integer stockCount) {
        super();
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

}
