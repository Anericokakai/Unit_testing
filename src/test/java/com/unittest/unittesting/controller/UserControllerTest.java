package com.unittest.unittesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.unittesting.Exceptions.UserNotFoundException;
import com.unittest.unittesting.Services.UserServiceImpl;
import com.unittest.unittesting.model.Role;
import com.unittest.unittesting.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;



    @MockBean
    UserServiceImpl userServiceImpl;
    Users users;
    Users validUser;

    @BeforeEach
    void setUp() {

        users= Users.builder()
                .name("anerico kakai").email("anericokakai4@gmail.com")
                .password("1")
                .role(Role.valueOf("ADMIN")).build();

         validUser= Users.builder()
                .name("anerico kakai").email("anericokakai4@gmail.com")
                .password("1234567")
                .role(Role.valueOf("ADMIN")).build();

    }

    @Test
    public void  testShouldReturn400BADrEQUEST() throws Exception {


        String requestBody=objectMapper.writeValueAsString(users);
        mockMvc.perform(post("/users/new").contentType("application/json").content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    public  void shouldReturn201Status() throws Exception{


        Mockito.when(userServiceImpl.saveUser(validUser)).thenReturn(validUser);
        String requestBody= objectMapper.writeValueAsString(validUser);


        mockMvc.perform(post("/users/new").
                        contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/users/"+validUser.getId())))
                .andExpect(jsonPath("$.email",is(validUser.getEmail())))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andDo(print());
    }


//    find by id

    @Test
    public  void TestShouldFailWithRequest404() throws Exception {


      Mockito.when(userServiceImpl.findUserById(validUser.getId())).thenThrow(Exception.class);

       String userId="120";

mockMvc.perform(
        get("/users/{userId}",userId)
        .contentType("application/json")
        ).andExpect(status().isBadRequest())
        .andDo(print());



    }


}

//
