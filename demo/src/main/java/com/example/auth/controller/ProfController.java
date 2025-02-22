package com.example.auth.controller;

import com.example.auth.model.Professors;
import com.example.auth.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PatchMapping("/add")
    public ResponseEntity<String> addProfessors(@RequestBody List<Professors> professorList) {
        if (professorList == null || professorList.isEmpty()) {
            return ResponseEntity.badRequest().body("Professor list cannot be empty");
        }

        for (Professors p : professorList) {
            if (p.getId() != null) {  // Ensure the professor has an ID
                Optional<Professors> existingProfessor = professorRepository.findById(p.getId());
                if (existingProfessor.isPresent()) {
                    Professors n = existingProfessor.get();
                    n.setCourse(p.getCourse()); 
                    n.setSection(p.getSection());
                    professorRepository.save(n);
                } else {
                    return ResponseEntity.badRequest().body("Professor with ID " + p.getId() + " not found");
                }
            } else {
                // If there's no ID, it's a new professor, so save it as a new record
                professorRepository.save(p);
            }
        }
    
        return ResponseEntity.ok("Professors added/updated successfully");
    }

 @PatchMapping("/set")
public ResponseEntity<String> NewsetEmail(@RequestParam String oldprofessorEmail, @RequestParam String newprofessorEmail) {
    List<Professors> professorsList = professorRepository.findByEmail(oldprofessorEmail);
    
    if (professorsList.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No professor found with the given email");
    }
    
    for (Professors professor : professorsList) {
        professor.setEmail(newprofessorEmail);
    }
    
    professorRepository.saveAll(professorsList);
    
    return ResponseEntity.ok("Professor's email updated successfully");
}
}
