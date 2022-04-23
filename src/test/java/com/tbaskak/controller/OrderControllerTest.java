package com.tbaskak.controller;

import com.tbaskak.entity.Order;
import com.tbaskak.authentication.RestAuthenticationEntryPoint;
import com.tbaskak.repository.UserRepository;
import com.tbaskak.service.BookService;
import com.tbaskak.service.CustomerService;
import com.tbaskak.service.OrderService;
import com.tbaskak.service.UserService;
import com.tbaskak.util.JwtUtility;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private JwtUtility jwtUtility;


    @MockBean
    private UserService userService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BookService bookService;

    @Before()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private OrderService orderService;
    Calendar cal = Calendar.getInstance();
    private final Order orderForTest = new Order("1","1",1,new Date(),1,new SimpleDateFormat("MMM").format(cal.getTime()), BigDecimal.valueOf(3));
    private final List<Order> allOrders = new ArrayList<>();

    @Test
    void getAllOrders() throws Exception {
        allOrders.add(orderForTest);
        Mockito.when(orderService.getAllOrders()).thenReturn(allOrders);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
}