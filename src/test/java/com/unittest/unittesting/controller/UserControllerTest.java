package com.unittest.unittesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittest.unittesting.Services.UserService;
import com.unittest.unittesting.model.Role;
import com.unittest.unittesting.model.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;

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
    UserService userService;
    @Test
    public void  testShouldReturn400BADrEQUEST() throws Exception {

        Users newUser= Users.builder()
                .name("anerico kakai").email("anericokakai4@gmail.com")
                .password("1")
                .role(Role.valueOf("ADMIN")).build();
        String requestBody=objectMapper.writeValueAsString(newUser);
        mockMvc.perform(post("/users/new").contentType("application/json").content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    public  void shouldReturn201Status() throws Exception{
        Users newUser= Users.builder()
                .name("anerico kakai").email("anericokakai4@gmail.com")
                .password("1234567")
                .role(Role.valueOf("ADMIN")).build();

        Mockito.when(userService.saveUser(newUser)).thenReturn(newUser);
        String requestBody= objectMapper.writeValueAsString(newUser);


        mockMvc.perform(post("/users/new").
                        contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is("/users/"+newUser.getId())))
                .andExpect(jsonPath("$.email",is(newUser.getEmail())))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andDo(print());
    }



}

//
