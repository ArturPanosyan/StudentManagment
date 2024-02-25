package com.example.studentmanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lesson")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime duration;

    private double price;

    private Date startDate;

    @ManyToMany(mappedBy = "lessonListAsStudent", fetch = FetchType.EAGER)
    private List<User> students;

    @ManyToOne
    private User teacher;
}
