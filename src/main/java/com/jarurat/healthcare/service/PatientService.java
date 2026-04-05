package com.jarurat.healthcare.service;

import com.jarurat.healthcare.model.Patient;
import com.jarurat.healthcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient save(Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new IllegalArgumentException("A patient with this email already exists.");
        }
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public long count() {
        return patientRepository.count();
    }

    public List<Object[]> countBySupportType() {
        return patientRepository.countBySupportType();
    }
}
