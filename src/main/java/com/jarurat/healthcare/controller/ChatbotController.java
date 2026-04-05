package com.jarurat.healthcare.controller;

import com.jarurat.healthcare.dto.ChatbotResponse;
import com.jarurat.healthcare.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    /**
     * AI FEATURE: FAQ Chatbot Endpoint
     * POST /api/chatbot/ask
     * Body: { "question": "How do I register?" }
     */
    @PostMapping("/ask")
    public ResponseEntity<ChatbotResponse> ask(@RequestBody Map<String, String> body) {
        String question = body.getOrDefault("question", "").trim();
        if (question.isEmpty()) {
            return ResponseEntity.badRequest().body(
                new ChatbotResponse("", "Please type a question.", "GENERAL", false)
            );
        }
        ChatbotResponse response = chatbotService.processQuestion(question);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "Jarurat Care FAQ Chatbot",
            "version", "1.0.0"
        ));
    }
}
