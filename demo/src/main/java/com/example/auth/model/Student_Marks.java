package com.example.auth.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Student_Marks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student_Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String usn;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String section;
    
    @Column(nullable = false)
    private String course;
    
    @Column(nullable = true)
    private int marks;
}
