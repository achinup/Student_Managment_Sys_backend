package com.example.auth.controller;
import com.example.auth.model.Student_Marks;
import com.example.auth.repository.StudentMarksRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
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

    @PatchMapping("/professor/update")
    public ResponseEntity<String> updateRecord(@RequestBody List<Student_Marks> studentList) {
        if (studentList == null || studentList.isEmpty()) {
            return ResponseEntity.badRequest().body("Marks list cannot be empty");
        }
    
        for (Student_Marks newRec : studentList) {

            if (newRec.getId() == null) {
                // If id is null, save as a new record
                stu.save(newRec);
                continue;
            }
            Optional<Student_Marks> existingData = stu.findById(newRec.getId()); // Using findById now
           
            if (existingData.isPresent()) {
                // Update the existing record
                Student_Marks markToUpdate = existingData.get();
                markToUpdate.setMarks(newRec.getMarks());
                markToUpdate.setSection(newRec.getSection());
                markToUpdate.setCourse(newRec.getCourse());
                stu.save(markToUpdate);
            } else {
                // Insert new record if it doesn't exist
                return ResponseEntity.badRequest().body("Student with ID " + newRec.getId() + " not found");
            }
        }
    
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

    @PatchMapping("/student/update-email")
public ResponseEntity<String> updateStudentEmail(@RequestParam String oldemail, @RequestParam String email) {
    List<Student_Marks> existingStudent = stu.findByEmail(oldemail);

     if (existingStudent.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No professor found with the given email");
    }
    
    for (Student_Marks Student : existingStudent) {
        Student.setEmail(email);
    }
    
    stu.saveAll(existingStudent);
    
    return ResponseEntity.ok("Professor's email updated successfully");
}

}
