package com.example.studentmanagment.service;

import com.example.studentmanagment.entity.User;
import com.example.studentmanagment.entity.UserType;
import com.example.studentmanagment.exceptions.EmailIsPresentException;
import com.example.studentmanagment.exceptions.PasswordNotMuchException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user, MultipartFile multipartFile, String confirmPassword) throws IOException, EmailIsPresentException, PasswordNotMuchException;
    User save(User user);
    User update(User user, MultipartFile multipartFile) throws IOException;
    Optional<User> findById(int id);
    List<User> findUserByUserType(UserType userType);
    void deletePicture(int id);
}