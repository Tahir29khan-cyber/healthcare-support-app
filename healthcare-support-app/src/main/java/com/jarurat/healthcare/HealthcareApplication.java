package com.jarurat.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Jarurat Care - Mini Healthcare Support Web App
 * Assignment: Full Stack Developer (AI-Enabled) Internship
 *
 * Features:
 *  - Patient Registration Form
 *  - Volunteer Registration Form
 *  - Contact / Support Form
 *  - AI-Powered FAQ Chatbot (rule-based NLP simulation)
 *  - Admin Dashboard with Data Summary
 */
@SpringBootApplication
public class HealthcareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("  Jarurat Care Healthcare App Started!");
        System.out.println("  URL: http://localhost:8080");
        System.out.println("  H2 Console: http://localhost:8080/h2-console");
        System.out.println("========================================\n");
    }
}
