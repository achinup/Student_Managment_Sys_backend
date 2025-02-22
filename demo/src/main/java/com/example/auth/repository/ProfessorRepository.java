package com.example.auth.repository;
import com.example.auth.model.Professors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professors,Long> {

    List<Professors> findByEmail(String email);
    Optional<Professors> findById(int id);
    Optional<Professors> findByCourseAndSection(String Course,String section);
}
