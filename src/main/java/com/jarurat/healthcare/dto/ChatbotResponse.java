package com.jarurat.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatbotResponse {
    private String question;
    private String answer;
    private String category;
    private boolean matched;
}
