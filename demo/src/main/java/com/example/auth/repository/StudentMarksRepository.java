package com.example.auth.repository;

import com.example.auth.model.Student_Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentMarksRepository extends JpaRepository<Student_Marks, Long> {

    List<Student_Marks> findByEmail(String email);
    
    List<Student_Marks> findByCourseAndSection(String course, String section);
    
    Optional<Student_Marks> findById(Long id);

    @Query("SELECT sm FROM Student_Marks sm WHERE sm.usn_section.usn = :usn")
    List<Student_Marks> findByUsn(@Param("usn") String usn);
    

   
}
