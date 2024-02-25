package com.example.studentmanagment.repository;

import com.example.studentmanagment.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findLessonByTeacherId(int userId);
    List<Lesson> findLessonByStudentsId(int userId);


}
