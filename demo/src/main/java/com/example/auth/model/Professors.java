package com.example.auth.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Professors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String course;

    @Column(nullable = false)
    private String section;
}
