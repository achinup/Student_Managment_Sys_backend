package com.example.auth.controller;

import com.example.auth.model.Professors;
import com.example.auth.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@CrossOrigin(origins = "http://localhost:3000") 
public class ProfController {
    
    
    public ProfessorRepository professorRepository;
    @Autowired
    public ProfController(ProfessorRepository professorRepository)
    {
        this.professorRepository=professorRepository;
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Professors>> getProfessorCourses(@PathVariable String email) {
        List<Professors> professors = professorRepository.findByEmail(email);
        if (professors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professors);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProfessors(@RequestBody List<Professors> professorList) {
        if (professorList == null || professorList.isEmpty()) {
            return ResponseEntity.badRequest().body("Professor list cannot be empty");
        }
        professorRepository.saveAll(professorList);
        return ResponseEntity.ok("Professors added successfully");
    }
}
