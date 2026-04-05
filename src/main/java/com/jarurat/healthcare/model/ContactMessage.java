package com.jarurat.healthcare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Subject is required")
    @Column(nullable = false)
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(min = 10, message = "Message must be at least 10 characters")
    @Column(nullable = false, length = 2000)
    private String message;

    // AI Feature: auto-generated response suggestion
    @Column(length = 2000)
    private String autoResponse;

    @Column(name = "status")
    private String status = "UNREAD";

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime submittedAt;
}
