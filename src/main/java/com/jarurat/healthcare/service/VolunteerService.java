package com.jarurat.healthcare.service;

import com.jarurat.healthcare.model.Volunteer;
import com.jarurat.healthcare.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public Volunteer save(Volunteer volunteer) {
        if (volunteerRepository.existsByEmail(volunteer.getEmail())) {
            throw new IllegalArgumentException("A volunteer with this email already exists.");
        }
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    public long count() {
        return volunteerRepository.count();
    }
}
