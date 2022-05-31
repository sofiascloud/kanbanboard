package com.niit.boardapplication.usertest.service;

import com.niit.boardapplication.exception.UserAlreadyExistsException;
import com.niit.boardapplication.exception.UserNotFoundException;
import com.niit.boardapplication.model.User;
import com.niit.boardapplication.repository.UserRepository;
import com.niit.boardapplication.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock //dependency
    private UserRepository userRepository;

    @InjectMocks //dependent
    private UserServiceImpl userService;

    //model class reference creation
    private User user;

    //run before every test case
    @BeforeEach
    public void resource() {
        user = new User(121, "Relaxed Carson", "abcd123");
    }

    //run after every test case
    @AfterEach
    public void restore() {
        user = null;
        userRepository.deleteAll();
    }

    @Test
    public void testAddUserReturnUser() throws UserAlreadyExistsException {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.createUser(user));
        verify(userRepository,times(1)).findById(user.getUserId());
        verify(userRepository,times(1)).save(user);

    }

    @Test
    public void testNegationAddUserReturnUser() throws UserAlreadyExistsException {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyExistsException.class,()->userService.createUser(user));
        verify(userRepository,times(1)).findById(user.getUserId());
        verify(userRepository,times(0)).save(user);
    }

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
        boolean result = userService.removeUser(user.getUserId());
        assertTrue(result);
        verify(userRepository,times(1)).findById(user.getUserId());
        verify(userRepository,times(1)).deleteById(user.getUserId());

    }

    @Test
    public void testFindbyUserId() throws UserNotFoundException {
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
        assertEquals(user,userService.showUser(user.getUserId()));
    }



}
