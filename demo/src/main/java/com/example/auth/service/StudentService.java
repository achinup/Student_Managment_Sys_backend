package com.example.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.auth.model.Usn_section;
import com.example.auth.model.Student_Marks;
import com.example.auth.repository.StudentMarksRepository;
import com.example.auth.repository.Usn_sectionRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class StudentService {
    @Autowired
    public Usn_sectionRepository studentRepository;

    @Autowired
    public StudentMarksRepository studentMarksRepository;

    // Add a new student
    // public Student addStudent(String usn, String section) {
    //     if (studentRepository.existsById(usn)) {
    //         throw new RuntimeException("Student with USN " + usn + " already exists.");
    //     }
    //     Student student = new Student(usn, section);
    //     return studentRepository.save(student);
    // }

    // Update student section and reflect it in StudentMarks
    public String updateStudent(String usn) {
        Optional<Usn_section> student = studentRepository.findById(usn);
               
        if(student.isPresent())
        {
            return student.get().getSection();
        }

        else

        return "notfounderror";
        
    
    }  


    public void update(Usn_section s) {  
    if (studentRepository.existsById(s.getUsn())) {  // Assuming 'usn' is the primary key
        studentRepository.save(s);

        // List<Student_Marks> marksList = studentMarksRepository.findByUsn_section_Usn(s.getUsn());
        // for (Student_Marks marks : marksList) {
        //     marks.setSection(s.getSection());
        //     studentMarksRepository.save(marks);
        // }

    } else {
        throw new EntityNotFoundException("Student not found with USN: " + s.getUsn());
    }
}


    public void add(Usn_section s)
    {
        
               
        if(studentRepository.existsById(s.getUsn()))
        {   
            throw new RuntimeException("Student with USN " + s.getUsn() + " already exists.");
            
        }

        else
        {
            studentRepository.save(s);
        }
    }
}

