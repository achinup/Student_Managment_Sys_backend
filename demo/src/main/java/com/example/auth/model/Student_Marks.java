package com.example.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Student_Marks", 
       uniqueConstraints = {@UniqueConstraint(columnNames = {"usn", "course"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student_Marks {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usn", nullable = false)
    private Usn_section usn_section; // Ensure Usn_section has @Column(name = "usn")

    @Column(nullable = false)
    private String section;
     
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String course;
    
    @Column(nullable = true)
    private Integer marks; // Changed to Integer for nullable values
}
