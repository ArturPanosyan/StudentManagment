package com.example.studentmanagment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String email;

    private String picName;

    private String password;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Lesson> lessonListAsTeacher;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_lesson",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private List<Lesson> lessonListAsStudent;

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.EAGER)
    private List<Message> sendMessages;

    @OneToMany(mappedBy = "toUser", fetch = FetchType.EAGER)
    private List<Message> receiveMessages;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
