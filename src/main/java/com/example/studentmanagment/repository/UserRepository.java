package com.example.studentmanagment.repository;

import com.example.studentmanagment.entity.User;
import com.example.studentmanagment.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    List<User> findUserByUserType(UserType userType);


}
