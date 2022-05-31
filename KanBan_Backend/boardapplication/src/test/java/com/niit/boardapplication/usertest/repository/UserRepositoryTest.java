package com.niit.boardapplication.usertest.repository;

import com.niit.boardapplication.model.User;
import com.niit.boardapplication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //reference creation
    private User user;

    @BeforeEach
    public void PreTest() {
        user = new User(121, "Relaxed Carson", "abcd123");
    }


    @AfterEach
    public void postTest() {

        user = null;
        userRepository.deleteAll();

    }

    @Test
    public void testUserIdNotNullAfterInsertingUserRecord() {
        userRepository.insert(user);
        User result = userRepository.findById(user.getUserId()).get();
        assertNotNull(result);
        //comparing expected and actual attribute values
        assertEquals(result.getUserId(), user.getUserId());

    }


    @Test
    public void negationTestUpdateUserName(){
        userRepository.insert(user); // has old data
        User user_test= userRepository.findById(user.getUserId()).get();
        user_test.setUserName("Dan Henderson");
        userRepository.save(user_test);
        assertNotEquals(user.getUserName(),
                userRepository.findById(user.getUserId()).get().getUserName());

    }

    @Test
    public void testUserNullAfterRemovingUserRecord() {
        userRepository.insert(user);
        userRepository.deleteById(user.getUserId());
        assertEquals(Optional.empty(),userRepository.findById(user.getUserId()));

    }
}

