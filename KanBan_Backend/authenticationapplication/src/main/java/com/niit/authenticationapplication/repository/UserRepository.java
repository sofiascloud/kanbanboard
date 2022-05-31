package com.niit.authenticationapplication.repository;

import com.niit.authenticationapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public abstract User findByEmailIdAndPassword(String emailId, String password);
//    public abstract User findByEmailId(String emailId);
}
