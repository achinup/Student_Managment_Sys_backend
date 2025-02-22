package com.example.auth.repository;


import com.example.auth.model.Student_Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentMarksRepository extends JpaRepository<Student_Marks,Long> {
    List<Student_Marks> findByEmail(String email);
    List<Student_Marks> findByCourseAndSection(String course, String section);
    
}
