package com.tbaskak.model.book;

import com.tbaskak.constant.Constant;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

public class BookSaveDto {
    @NotNull
    @NotEmpty
    @Size(min = Constant.MIN_LENGTH,max = Constant.MAX_LENGTH)
    private String name;
    @NotNull
    @Min(1)
    @Field(targetType = DECIMAL128)
    private BigDecimal price;
    @NotNull
    @Min(1)
    private Integer stockCount;

    public BookSaveDto() {
    }

    public BookSaveDto(String name, BigDecimal price, Integer stockCount) {
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
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
