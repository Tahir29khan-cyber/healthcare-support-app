package com.jarurat.healthcare.controller;

import com.jarurat.healthcare.model.Volunteer;
import com.jarurat.healthcare.service.VolunteerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/volunteer")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    private static final List<String> SKILL_OPTIONS = Arrays.asList(
            "Doctor / Medical Professional", "Nurse / Paramedic", "Social Worker",
            "Pharmacist", "Counselor / Therapist", "Data Entry / Admin",
            "Driver / Logistics", "Translation Support", "IT / Tech Support", "General Helper"
    );

    private static final List<String> AVAILABILITY_OPTIONS = Arrays.asList(
            "Weekdays (9 AM – 5 PM)", "Weekends Only", "Evenings (6 PM – 10 PM)",
            "Flexible / Anytime", "On-Call Emergency"
    );

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("skillOptions", SKILL_OPTIONS);
        model.addAttribute("availabilityOptions", AVAILABILITY_OPTIONS);
        return "volunteer-form";
    }

    @PostMapping("/register")
    public String submitForm(
            @Valid @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("skillOptions", SKILL_OPTIONS);
            model.addAttribute("availabilityOptions", AVAILABILITY_OPTIONS);
            return "volunteer-form";
        }
        try {
            volunteerService.save(volunteer);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Thank you for volunteering! We'll reach out to you soon. ❤️");
            return "redirect:/volunteer/register?success";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("skillOptions", SKILL_OPTIONS);
            model.addAttribute("availabilityOptions", AVAILABILITY_OPTIONS);
            return "volunteer-form";
        }
    }
}
