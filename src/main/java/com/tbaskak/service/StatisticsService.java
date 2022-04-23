package com.tbaskak.service;

import com.tbaskak.constant.Constant;
import com.tbaskak.entity.Order;
import com.tbaskak.exception.CustomException;
import com.tbaskak.model.statistic.StatisticsDto;
import com.tbaskak.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    OrderRepository orderRepository;

    public List<StatisticsDto> getStatistics() throws CustomException {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty())
            throw new CustomException(Constant.ORDER_NOT_FOUND);

        Map<String,List<Order>> groupByMountOrders =  orders.stream().collect(Collectors.groupingBy(Order::getOrderMount));
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for (String mount : groupByMountOrders.keySet()) {
            int totalOrderCountPerMount =groupByMountOrders.get(mount).size();
            int totalBookCountPerMount = groupByMountOrders.get(mount).stream().mapToInt(Order::getorderCount).sum();
            BigDecimal totalPurchasedAmountPerMount = groupByMountOrders.get(mount).stream().map(x->x.getBookPrice().multiply(BigDecimal.valueOf(x.getorderCount()))).reduce(BigDecimal.ZERO,BigDecimal::add);

            StatisticsDto statisticsDto = new StatisticsDto(mount,totalPurchasedAmountPerMount,totalOrderCountPerMount,totalBookCountPerMount);
            statisticsDtos.add(statisticsDto);
        }
        return statisticsDtos;
    }

}
