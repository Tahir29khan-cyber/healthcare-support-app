package com.jarurat.healthcare.repository;

import com.jarurat.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByStatus(String status);

    @Query("SELECT p.supportType, COUNT(p) FROM Patient p GROUP BY p.supportType")
    List<Object[]> countBySupportType();

    boolean existsByEmail(String email);
}
