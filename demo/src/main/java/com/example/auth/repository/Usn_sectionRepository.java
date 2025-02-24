package com.example.auth.repository;
import com.example.auth.model.Usn_section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface Usn_sectionRepository extends JpaRepository<Usn_section, String> {
    Optional<Usn_section> findById(String usn);
}
