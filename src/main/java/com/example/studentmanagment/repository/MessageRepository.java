package com.example.studentmanagment.repository;

import com.example.studentmanagment.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findMessageByFromUserId(int id);
    List<Message> findMessageByToUserId(int id);

}
