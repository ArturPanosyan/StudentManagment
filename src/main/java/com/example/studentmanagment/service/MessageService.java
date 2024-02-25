package com.example.studentmanagment.service;

import com.example.studentmanagment.entity.Message;

import java.util.List;

public interface MessageService {
    Message save(Message message);
    List<Message> findMessageByFromUserId(int id);
    List<Message> findMessageByToUserId(int id);
}