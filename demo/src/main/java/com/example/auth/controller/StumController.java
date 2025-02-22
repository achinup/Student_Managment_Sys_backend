package com.example.auth.controller;

import com.example.auth.model.Student_Marks;
import com.example.auth.repository.StudentMarksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/student-marks")
@CrossOrigin(origins = "http://localhost:3000")
public class StumController {

    public  StudentMarksRepository stu;

    @Autowired
    public StumController(StudentMarksRepository stu) {
        this.stu = stu;
    }

    @GetMapping("/student/{email}")
    public ResponseEntity<List<Student_Marks>> getStudentMark(@PathVariable String email) {
        List<Student_Marks> marks = stu.findByEmail(email);
        if (marks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marks);
    }

    @PostMapping("/professor/update")
    public ResponseEntity<String> updateMarks(@RequestBody List<Student_Marks> marksList) {
        if (marksList == null || marksList.isEmpty()) {
            return ResponseEntity.badRequest().body("Marks list cannot be empty");
        }
        stu.saveAll(marksList);
        return ResponseEntity.ok("Marks updated successfully");
    }

    @GetMapping("/professor/stumark")
    public ResponseEntity<List<Student_Marks>> getCourseSectionMark(@RequestParam String course, @RequestParam String section) {
        List<Student_Marks> marks = stu.findByCourseAndSection(course, section);
        if (marks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marks);
    }

    @PostMapping("/professor/add")
    public ResponseEntity<String> addNewStudentMarks(@RequestBody List<Student_Marks> marksList) {
        if (marksList == null || marksList.isEmpty()) {
            return ResponseEntity.badRequest().body("Marks list cannot be empty");
        }
        stu.saveAll(marksList);
        return ResponseEntity.ok("New student marks added successfully");
    }
}
