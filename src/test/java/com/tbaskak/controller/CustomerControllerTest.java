package com.tbaskak.controller;

import com.tbaskak.authentication.RestAuthenticationEntryPoint;
import com.tbaskak.entity.Customer;
import com.tbaskak.repository.UserRepository;
import com.tbaskak.service.CustomerService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private JwtUtility jwtUtility;

    @MockBean
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @MockBean
    private UserService userService;


    @MockBean
    private UserRepository userRepository;

    @Before()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private CustomerService customerService;

    private final Customer customerForTest = new Customer("tempCustomer@mail.com","customerFirstName","customerLastName");
    private final List<Customer> allCustomers = new ArrayList<>();

    @Test
    void save() throws Exception {
        Mockito.when(customerService.save(new Customer())).thenReturn(customerForTest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customerSave")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tempCustomer@mail.com\",\"firstName\":\"customerFirstName\",\"lastName\":\"customerLastName\"}");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void getCustomers() throws Exception {
        allCustomers.add(customerForTest);
        Mockito.when(customerService.allCustomer()).thenReturn(allCustomers);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allCustomers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
}