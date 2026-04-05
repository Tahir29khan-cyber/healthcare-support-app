package com.jarurat.healthcare.controller;

import com.jarurat.healthcare.model.Patient;
import com.jarurat.healthcare.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("supportTypes", Patient.SupportType.values());
        return "patient-form";
    }

    @PostMapping("/register")
    public String submitForm(
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("supportTypes", Patient.SupportType.values());
            return "patient-form";
        }
        try {
            patientService.save(patient);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Registration successful! Our team will contact you within 24 hours. 🙏");
            return "redirect:/patient/register?success";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("supportTypes", Patient.SupportType.values());
            return "patient-form";
        }
    }
}
