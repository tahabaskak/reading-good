package com.tbaskak.service;

import com.tbaskak.constant.Constant;
import com.tbaskak.constant.OrderStatus;
import com.tbaskak.entity.Customer;
import com.tbaskak.entity.Order;
import com.tbaskak.exception.CustomException;
import com.tbaskak.entity.Book;
import com.tbaskak.model.order.OrderDto;
import com.tbaskak.repository.BookRepository;
import com.tbaskak.repository.CustomerRepository;
import com.tbaskak.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    public Order getOrderById(String id) throws CustomException {
        Optional<Order> orderModel = orderRepository.findById(id);
        if(orderModel.isEmpty())
            throw new CustomException(Constant.ORDER_NOT_FOUND);
        return orderModel.get();
    }

    public Order save(OrderDto model) throws CustomException {
        Optional<Customer> customer = customerRepository.findById(model.getCustomerId());

        if(customer.isEmpty())
            throw new CustomException(Constant.CUSTOMER_NOT_FOUND);

        Optional<Book> book = bookRepository.findById(model.getBookId());

        if(book.isEmpty())
            throw new CustomException(Constant.BOOK_NOT_FOUND);

        if(book.get().getStockCount() < model.getOrderCount())
            throw new CustomException(Constant.STOCK_IS_NOT_AVAILABLE);

        Calendar cal = Calendar.getInstance();

        Order order = new Order(model.getBookId(), model.getCustomerId(), model.getOrderCount(), new Date(), OrderStatus.CREATED,
                new SimpleDateFormat("MMM").format(cal.getTime()), book.get().getPrice());

        decreaseAndCheckStock(book.get(),order);

        return orderRepository.save(order);
    }

    private synchronized void decreaseAndCheckStock(Book book,Order order) throws CustomException{
        if(book.getStockCount()-order.getorderCount()<0){
            throw new CustomException(Constant.STOCK_IS_NOT_AVAILABLE);
        }
        book.setStockCount(book.getStockCount()-order.getorderCount());
        bookRepository.save(book);
    }

    public Order delete(String id) throws CustomException {
        Optional<Order> orderModel = orderRepository.findById(id);

        if(orderModel.isEmpty())
            throw new CustomException(Constant.ORDER_NOT_FOUND);

        Order order = orderModel.get();

        Optional<Book> book = bookRepository.findById(order.getBookId());

        if(book.isEmpty())
            throw new CustomException(Constant.BOOK_NOT_FOUND);

        increaseStock(book.get(),order);
        orderRepository.deleteById(order.getId());

        return order;
    }

    public Order cancel(String id) throws CustomException {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isEmpty())
            throw new CustomException(Constant.ORDER_NOT_FOUND);
        Optional<Book> book = bookRepository.findById(order.get().getBookId());

        if(book.isEmpty())
            throw new CustomException(Constant.BOOK_NOT_FOUND);

        increaseStock(book.get(),order.get());

        order.get().setStatus(OrderStatus.CANCELED);

        return orderRepository.save(order.get());
    }

    private synchronized void increaseStock(Book book,Order order) throws CustomException{
        book.setStockCount(book.getStockCount()+order.getorderCount());
        bookRepository.save(book);
    }

    public List<Order> getOrdersWithDates(Date from,Date to) throws CustomException{
        if(from.getTime()>to.getTime())
            throw new CustomException(Constant.START_DATE_IS_NOT_BIGGER_THEN_END_DATE);
        List<Order> orders = orderRepository.findByOrdersWithDate(from,to);
        if(orders.isEmpty())
            throw new CustomException(Constant.ORDER_NOT_FOUND);
        return orders;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
