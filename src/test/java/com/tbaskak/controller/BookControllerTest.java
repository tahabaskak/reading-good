package com.tbaskak.controller;

import com.tbaskak.authentication.RestAuthenticationEntryPoint;
import com.tbaskak.entity.Book;
import com.tbaskak.model.book.BookSaveDto;
import com.tbaskak.repository.UserRepository;
import com.tbaskak.service.BookService;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private JwtUtility jwtUtility;


    @MockBean
    private UserService userService;


    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Before()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private BookService bookService;


    private final Book bookForTest = new Book("tempBookName", BigDecimal.valueOf(5),3);
    private final List<Book> allBooks = new ArrayList<>();

    @Test
    void save() throws Exception {
        Mockito.when(bookService.save(new BookSaveDto())).thenReturn(bookForTest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bookSave")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"tempBookName\",\"price\":\"5\",\"stockCount\":\"3\"}");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void getBooks() throws Exception {
        allBooks.add(bookForTest);
        Mockito.when(bookService.allBooks()).thenReturn( allBooks);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getBooks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
}