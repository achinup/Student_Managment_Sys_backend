package com.example.auth.repository;
import com.example.auth.model.Professors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professors,Long> {

    List<Professors> findByEmail(String email);
    
}
