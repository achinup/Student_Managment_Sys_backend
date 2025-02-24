package com.example.auth.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usn_section", uniqueConstraints = {@UniqueConstraint(columnNames = "usn")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usn_section {
    @Id
    private String usn; // Primary key
    
    @Column(nullable = false)
    private String section; 
}
