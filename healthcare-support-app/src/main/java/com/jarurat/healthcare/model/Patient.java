package com.jarurat.healthcare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit Indian mobile number")
    @Column(nullable = false)
    private String phone;

    @NotNull(message = "Age is required")
    @Min(value = 1)
    @Max(value = 120)
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "Please describe your medical concern")
    @Column(nullable = false, length = 1000)
    private String medicalConcern;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupportType supportType;

    private String city;

    @Column(name = "status")
    private String status = "PENDING";

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime registeredAt;

    public enum SupportType {
        MEDICAL_CONSULTATION,
        MEDICINE_ASSISTANCE,
        HOMECARE_SUPPORT,
        MENTAL_HEALTH,
        EMERGENCY_HELP,
        OTHER
    }
}
