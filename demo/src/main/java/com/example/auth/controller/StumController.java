package com.example.auth.controller;

import com.example.auth.model.Student_Marks;
import com.example.auth.repository.StudentMarksRepository;
import com.example.auth.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student-marks")
@CrossOrigin(origins = "http://localhost:3000")
public class StumController {

    public final StudentMarksRepository stu;
    public final StudentService studentService;

    @Autowired
    public StumController(StudentMarksRepository stu, StudentService studentService) {
        this.stu = stu;
        this.studentService = studentService;
    }

    // Get marks by student email
    @GetMapping("/student/{email}")
    public ResponseEntity<List<Student_Marks>> getStudentMark(@PathVariable String email) {
        List<Student_Marks> marks = stu.findByEmail(email);
        return marks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(marks);
    }

    // Update or insert marks
    @PatchMapping("/professor/update")
    public ResponseEntity<String> updateRecord(@RequestBody List<Student_Marks> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            return ResponseEntity.badRequest().body("Marks list cannot be empty");
        }

        for (Student_Marks newRec : studentList) {
            if (newRec.getId() == null) {  // Insert new record if ID is null
                String section = studentService.updateStudent(newRec.getUsn_section().getUsn());

                if ("notfounderror".equals(section)) {
                    continue;
                } else {
                    newRec.setSection(section);
                    stu.save(newRec);
                    continue;
                }
            }

            Optional<Student_Marks> existingData = stu.findById(newRec.getId());

            if (existingData.isPresent()) {
                Student_Marks markToUpdate = existingData.get();
                markToUpdate.setMarks(newRec.getMarks());
                markToUpdate.setCourse(newRec.getCourse());
                stu.save(markToUpdate);
            } else {
                return ResponseEntity.badRequest().body("Student with ID " + newRec.getId() + " not found");
            }
        }

        return ResponseEntity.ok("Marks updated successfully");
    }

    // Get marks by course and section
    @GetMapping("/professor/stumark")
    public ResponseEntity<List<Student_Marks>> getCourseSectionMark(@RequestParam String course, @RequestParam String section) {
        List<Student_Marks> marks = stu.findByCourseAndSection(course, section);
        return marks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(marks);
    }

    // Update student email
    @PatchMapping("/student/update-email")
    public ResponseEntity<String> updateStudentEmail(@RequestParam String oldemail, @RequestParam String email) {
        List<Student_Marks> existingStudent = stu.findByEmail(oldemail);

        if (existingStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found with the given email");
        }

        for (Student_Marks student : existingStudent) {
            student.setEmail(email);
        }

        stu.saveAll(existingStudent);
        return ResponseEntity.ok("Student's email updated successfully");
    }
}
