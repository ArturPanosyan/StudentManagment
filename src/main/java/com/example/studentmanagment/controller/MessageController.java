package com.example.studentmanagment.controller;

import com.example.studentmanagment.entity.Message;
import com.example.studentmanagment.entity.User;
import com.example.studentmanagment.security.SpringUser;
import com.example.studentmanagment.service.MessageService;
import com.example.studentmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/classmates")
    public String classmates() {
        return "classmates";
    }

    @GetMapping("/chat/{id}")
    public String chat(@PathVariable("id") int id, ModelMap modelMap, @AuthenticationPrincipal SpringUser springUser) {
        User fromUser = springUser.getUser();
        Optional<User> byId = userService.findById(id);

        if (byId.isPresent()) {
            User toUser = byId.get();
            modelMap.addAttribute("fromUser", fromUser);
            modelMap.addAttribute("toUser", toUser);
            modelMap.addAttribute("sendMessages", fromUser.getSendMessages());
            modelMap.addAttribute("receiveMessages", fromUser.getReceiveMessages());
            return "chat";
        }
        return "redirect:/classmates";
    }

    @PostMapping("/send")
    public String send(@ModelAttribute Message message, @RequestParam String sendMessage) {
        Optional<User> fromUserOptional = userService.findById(message.getFromUser().getId());
        Optional<User> toUserOptional = userService.findById(message.getToUser().getId());

        if (fromUserOptional.isPresent() && toUserOptional.isPresent()) {
            message.setFromUser(fromUserOptional.get());
            message.setToUser(toUserOptional.get());
            message.setMessage(sendMessage);

            fromUserOptional.get().getSendMessages().add(message);
            toUserOptional.get().getReceiveMessages().add(message);

            messageService.save(message);
            return "redirect:/chat/" + toUserOptional.get().getId();
        }
        return "redirect:/classmates";
    }
}