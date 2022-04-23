package com.tbaskak.model.statistic;

import java.math.BigDecimal;

public class StatisticsDto {
    private String mount;
    private BigDecimal totalPurchasedAmountPerMount;
    private Integer totalOrderCountPerMount;
    private Integer totalBookCountPerMount;

    public StatisticsDto() {
    }

    public StatisticsDto(String mount, BigDecimal totalPurchasedAmountPerMount, Integer totalOrderCountPerMount, Integer totalBookCountPerMount) {
        this.mount = mount;
        this.totalPurchasedAmountPerMount = totalPurchasedAmountPerMount;
        this.totalOrderCountPerMount = totalOrderCountPerMount;
        this.totalBookCountPerMount = totalBookCountPerMount;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public BigDecimal getTotalPurchasedAmountPerMount() {
        return totalPurchasedAmountPerMount;
    }

    public void setTotalPurchasedAmountPerMount(BigDecimal totalPurchasedAmountPerMount) {
        this.totalPurchasedAmountPerMount = totalPurchasedAmountPerMount;
    }

    public Integer getTotalOrderCountPerMount() {
        return totalOrderCountPerMount;
    }

    public void setTotalOrderCountPerMount(Integer totalOrderCountPerMount) {
        this.totalOrderCountPerMount = totalOrderCountPerMount;
    }

    public Integer getTotalBookCountPerMount() {
        return totalBookCountPerMount;
    }

    public void setTotalBookCountPerMount(Integer totalBookCountPerMount) {
        this.totalBookCountPerMount = totalBookCountPerMount;
    }
}
