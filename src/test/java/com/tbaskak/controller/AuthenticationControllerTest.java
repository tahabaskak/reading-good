package com.tbaskak.controller;

import com.tbaskak.authentication.RestAuthenticationEntryPoint;
import com.tbaskak.entity.User;
import com.tbaskak.repository.UserRepository;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.NoSuchAlgorithmException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtility jwtUtility;

    @MockBean
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    private final User userForTest = new User("tempFirstName","tempLastName","test@test.com","test");

    AuthenticationControllerTest() throws NoSuchAlgorithmException {
    }

    @Test
    void authenticate() throws Exception {
        Mockito.when(userService.login("test@test.com","test")).thenReturn(userForTest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("\"email\":\"test@test.com\",\"password\":\"test\"}");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void signup() throws Exception {
        Mockito.when(userService.singUp(new User())).thenReturn(userForTest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"tempFirstName\",\"lastName\":\"tempLastName\",\"email\":\"test@test.com\",\"password\":\"test\"}");
        mockMvc.perform(requestBuilder)
            .andExpect(status().isCreated())
            .andReturn();
    }

}