package com.niit.authenticationapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.niit.authenticationapplication.model.User;
import com.niit.authenticationapplication.services.UserServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    //to run the controller standalone
    @Autowired
    MockMvc mockMvc;

    @Mock
    private UserServices userServices;

    @InjectMocks
    private UserController userController;

    //reference for User
    private User user1, user2;

    @BeforeEach
    public void setUp(){
        user1= new User(1,"kevin", "kevin@gmail.com", "kev123");
        user2= new User(2,"Kevin Mathew", "kevin@gmail.com",
                "kev123");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void destroy(){
        user1=null;
        user2=null;
    }

    @Test
    public void givenUserDetailsForRegistration() throws Exception{
        when(userServices.registerUser(any())).thenReturn(user1);
        mockMvc.perform(
                post("/user/auth/register")
                .contentType(MediaType.APPLICATION_JSON) //requestbody
                .content(jsonToString(user1)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userServices,times(1)).registerUser(any());
    }


    //user defined method to convert json to string
    private static String jsonToString(final Object object) throws JsonProcessingException
    {
        String result="";
        try {
            //Creating an obj of object mapper class
            ObjectMapper mapper = new ObjectMapper();
            //converting json data into using ObjectMapper class
            String jsonContent = mapper.writeValueAsString(object);
            result=jsonContent;
            return result;
        }
        catch (JsonProcessingException e)
        {
            result="JsonProcessingException";
        }
        return result;
    }
}
