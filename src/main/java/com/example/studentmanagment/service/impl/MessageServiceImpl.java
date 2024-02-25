package com.example.studentmanagment.service.impl;

import com.example.studentmanagment.entity.Message;
import com.example.studentmanagment.repository.MessageRepository;
import com.example.studentmanagment.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        message.setLocalDateTime(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findMessageByFromUserId(int id) {
        return messageRepository.findMessageByFromUserId(id);
    }

    @Override
    public List<Message> findMessageByToUserId(int id) {
        return messageRepository.findMessageByToUserId(id);
    }
}