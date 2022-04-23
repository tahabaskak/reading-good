package com.tbaskak.service;

import com.tbaskak.constant.Constant;
import com.tbaskak.entity.Customer;
import com.tbaskak.exception.CustomException;
import com.tbaskak.model.customer.CustomerEmailDto;
import com.tbaskak.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> allCustomer(){
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) throws CustomException {
        if(Objects.nonNull(customerRepository.findByEmail(customer.getEmail()))){
            throw new CustomException(Constant.CUSTOMER_ALREADY_EXIST);
        }
        return customerRepository.save(customer);
    }

    public Customer update(Customer customerUpdate) throws CustomException {
        Customer result = checkCustomer(customerUpdate.getEmail());
        result.setFirstName(customerUpdate.getFirstName());
        result.setLastName(customerUpdate.getLastName());
        customerRepository.save(result);
        return result;
    }

    public Customer getCustomerByEmail(CustomerEmailDto customerEmailDto) throws CustomException{
        return checkCustomer(customerEmailDto.getEmail());
    }

    private Customer checkCustomer(String email) throws CustomException {
        Customer result = customerRepository.findByEmail(email);
        if(Objects.isNull(result)){
            throw new CustomException(Constant.CUSTOMER_NOT_FOUND);
        }
        return result;
    }
}
