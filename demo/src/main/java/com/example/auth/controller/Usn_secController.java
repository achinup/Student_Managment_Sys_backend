
package com.example.auth.controller;

import com.example.auth.model.Usn_section;
import com.example.auth.repository.Usn_sectionRepository;
import com.example.auth.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/student-section")
@CrossOrigin(origins = "http://localhost:3000")
public class Usn_secController {
    public final Usn_sectionRepository usr;
    public final StudentService studentService;

    @Autowired
    public Usn_secController(Usn_sectionRepository usr, StudentService studentService) {
        this.usr = usr;
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> postMethodName(@RequestBody List<Usn_section> Us) {
      if(Us == null || Us.isEmpty())
      ResponseEntity.badRequest().body("Marks list cannot be empty");
        for(Usn_section u:Us)
        {
            studentService.add(u);
        }
        
         return ResponseEntity.ok("Marks updated successfully");
    }
    
}
