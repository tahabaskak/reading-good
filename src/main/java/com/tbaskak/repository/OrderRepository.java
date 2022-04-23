package com.tbaskak.repository;

import com.tbaskak.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{'orderDate' : { $gte: ?0, $lte: ?1 } }")
    List<Order> findByOrdersWithDate(Date from,Date to);
}
