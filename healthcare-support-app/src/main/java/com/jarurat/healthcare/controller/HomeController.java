package com.jarurat.healthcare.controller;

import com.jarurat.healthcare.service.ContactService;
import com.jarurat.healthcare.service.PatientService;
import com.jarurat.healthcare.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PatientService patientService;
    private final VolunteerService volunteerService;
    private final ContactService contactService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalPatients", patientService.count());
        model.addAttribute("totalVolunteers", volunteerService.count());
        model.addAttribute("totalMessages", contactService.count());
        model.addAttribute("unreadMessages", contactService.countUnread());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("volunteers", volunteerService.findAll());
        model.addAttribute("messages", contactService.findAll());
        model.addAttribute("supportStats", patientService.countBySupportType());
        return "dashboard";
    }
}
