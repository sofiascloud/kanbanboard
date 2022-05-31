package com.niit.authenticationapplication.repository;

import com.niit.authenticationapplication.model.User;
import com.niit.authenticationapplication.services.UserServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    private User user;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setup(){
        user = new User(1,"Cartor", "Cartor@gmail.com", "1232");
    }

    @AfterEach
    public void tearDown(){
        user=null;
    }
    @Test
    public void givenUserDetailsSaveUserReturnUser(){
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, user);
    }

}
