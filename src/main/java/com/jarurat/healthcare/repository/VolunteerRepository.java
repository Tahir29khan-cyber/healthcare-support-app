package com.jarurat.healthcare.repository;

import com.jarurat.healthcare.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    List<Volunteer> findByStatus(String status);
    boolean existsByEmail(String email);
}
