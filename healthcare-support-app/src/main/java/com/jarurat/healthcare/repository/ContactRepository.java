package com.jarurat.healthcare.repository;

import com.jarurat.healthcare.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByStatus(String status);
    long countByStatus(String status);
}
