package com.tbaskak.model.order;

import java.util.Date;

public class FilterOrderDto {
    Date startDate;
    Date endDate;

    public FilterOrderDto() {
    }

    public FilterOrderDto(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
