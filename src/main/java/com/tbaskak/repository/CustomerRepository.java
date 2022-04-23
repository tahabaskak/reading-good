package com.tbaskak.repository;

import com.tbaskak.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByEmail(String email);

}
