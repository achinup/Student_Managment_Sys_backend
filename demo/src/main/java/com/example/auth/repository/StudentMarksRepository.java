package com.example.auth.repository;


import com.example.auth.model.Student_Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentMarksRepository extends JpaRepository<Student_Marks,Long> {
    List<Student_Marks> findByEmail(String email);
    List<Student_Marks> findByCourseAndSection(String course, String section);
    Optional<Student_Marks> findByUsnAndCourse(String usn, String course);
    Optional<Student_Marks>  findById(Long id);
    
}
