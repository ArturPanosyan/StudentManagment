package com.example.studentmanagment.controller;

import com.example.studentmanagment.entity.UserType;
import com.example.studentmanagment.security.SpringUser;
import com.example.studentmanagment.service.LessonService;
import com.example.studentmanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final UserService userService;
    private final LessonService lessonService;


    @GetMapping("/students")
    public String addStudentsPage(ModelMap modelMap) {
        modelMap.addAttribute("usersType", userService.findUserByUserType(UserType.STUDENT));
        return "students";
    }

    @PostMapping("/students/add")
    public String addStudents(@ModelAttribute UserType userType,
                              @RequestParam SpringUser springUser) {

        return null;
    }

}

