package com.jarurat.healthcare.controller;

import com.jarurat.healthcare.model.ContactMessage;
import com.jarurat.healthcare.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping
    public String submitForm(
            @Valid @ModelAttribute("contactMessage") ContactMessage contactMessage,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "contact";
        }
        contactService.save(contactMessage);
        redirectAttributes.addFlashAttribute("successMessage",
                "Message sent! We'll reply within 24 hours. An auto-response has been noted. ✅");
        return "redirect:/contact?success";
    }
}
