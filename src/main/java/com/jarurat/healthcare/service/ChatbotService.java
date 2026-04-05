package com.jarurat.healthcare.service;

import com.jarurat.healthcare.dto.ChatbotResponse;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * AI FEATURE: Rule-Based FAQ Chatbot Service
 *
 * This simulates an AI FAQ assistant using keyword matching and
 * intent classification. In production this could be replaced with
 * a real LLM API (e.g., OpenAI, Google Gemini) or a trained model.
 *
 * Intent categories: REGISTRATION, ELIGIBILITY, SERVICES,
 *                    VOLUNTEER, EMERGENCY, GENERAL
 */
@Service
public class ChatbotService {

    // ── Knowledge Base ─────────────────────────────────────────
    private static final List<FAQEntry> FAQ_DATABASE = new ArrayList<>();

    static {
        // Registration FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("register", "sign up", "enroll", "join", "registration", "how to register"),
                "REGISTRATION",
                "To register, click the 'Patient Registration' button on our homepage and fill in your details. " +
                "You'll need your name, email, phone number, and a brief description of your medical concern. " +
                "Registration is completely free!"
        ));

        FAQ_DATABASE.add(new FAQEntry(
                List.of("volunteer", "help", "contribute", "join as volunteer", "volunteer registration"),
                "VOLUNTEER",
                "We welcome volunteers! Click 'Volunteer Registration' and tell us about your background and availability. " +
                "We need doctors, nurses, social workers, and general helpers. Every skill is valued at Jarurat Care."
        ));

        // Services FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("service", "services", "offer", "provide", "what do you do", "help available"),
                "SERVICES",
                "Jarurat Care offers: 🏥 Medical Consultation support, 💊 Medicine Assistance, 🏠 Home Care Support, " +
                "🧠 Mental Health resources, 🚨 Emergency Help coordination, and General Health Guidance."
        ));

        FAQ_DATABASE.add(new FAQEntry(
                List.of("free", "cost", "charge", "fee", "price", "paid", "money"),
                "SERVICES",
                "Our patient support services are completely FREE. Jarurat Care is an NGO committed to making " +
                "healthcare accessible to everyone regardless of financial status."
        ));

        // Eligibility FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("eligible", "eligibility", "who can", "qualify", "criteria", "requirements"),
                "ELIGIBILITY",
                "Anyone in need of healthcare support can reach out to us. We prioritize underprivileged communities, " +
                "elderly individuals, and those without access to regular healthcare. There are no strict eligibility barriers."
        ));

        // Emergency FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("emergency", "urgent", "critical", "immediate", "ambulance", "serious"),
                "EMERGENCY",
                "🚨 For medical emergencies, please IMMEDIATELY call: 112 (National Emergency) or 102 (Ambulance). " +
                "Contact us via the emergency form for coordination support. Do not wait – call emergency services first!"
        ));

        // Contact FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("contact", "reach", "email", "phone", "address", "location", "office"),
                "GENERAL",
                "You can reach Jarurat Care via our Contact form on this website. Fill in your name, email, and message " +
                "and our team will respond within 24 hours. For urgent matters, mark the subject as URGENT."
        ));

        // Medicine FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("medicine", "medication", "drug", "prescription", "tablet", "treatment"),
                "SERVICES",
                "We can help coordinate medicine assistance for patients who cannot afford prescribed medications. " +
                "Register as a patient and select 'Medicine Assistance' as your support type. Our team will follow up."
        ));

        // Mental health FAQs
        FAQ_DATABASE.add(new FAQEntry(
                List.of("mental", "depression", "anxiety", "stress", "psychological", "counseling", "therapy"),
                "SERVICES",
                "Mental health is as important as physical health. We offer connections to certified counselors and " +
                "mental health professionals. Register with 'Mental Health' as your concern and we'll match you with support."
        ));

        // How it works
        FAQ_DATABASE.add(new FAQEntry(
                List.of("how", "process", "work", "step", "steps", "procedure", "workflow"),
                "GENERAL",
                "How it works: 1️⃣ Register as a Patient → 2️⃣ Describe your concern → 3️⃣ Our team reviews your case " +
                "→ 4️⃣ A volunteer/professional is assigned → 5️⃣ You receive support. Simple and free!"
        ));

        // Greeting
        FAQ_DATABASE.add(new FAQEntry(
                List.of("hello", "hi", "hey", "namaste", "good morning", "good afternoon"),
                "GENERAL",
                "Namaste! 🙏 Welcome to Jarurat Care. I'm your health support assistant. " +
                "You can ask me about our services, registration process, volunteer opportunities, or anything else!"
        ));

        // Thanks
        FAQ_DATABASE.add(new FAQEntry(
                List.of("thank", "thanks", "thank you", "धन्यवाद", "shukriya"),
                "GENERAL",
                "You're most welcome! 😊 At Jarurat Care, we exist to serve. Is there anything else I can help you with?"
        ));
    }

    // ── Main method ────────────────────────────────────────────
    public ChatbotResponse processQuestion(String userQuestion) {
        if (userQuestion == null || userQuestion.trim().isEmpty()) {
            return new ChatbotResponse(userQuestion, getDefaultResponse(), "GENERAL", false);
        }

        String normalized = userQuestion.toLowerCase().trim();

        // Score each FAQ entry
        FAQEntry bestMatch = null;
        int bestScore = 0;

        for (FAQEntry entry : FAQ_DATABASE) {
            int score = calculateScore(normalized, entry.keywords);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = entry;
            }
        }

        if (bestMatch != null && bestScore > 0) {
            return new ChatbotResponse(userQuestion, bestMatch.answer, bestMatch.category, true);
        }

        // Fallback
        return new ChatbotResponse(
                userQuestion,
                "I'm not sure about that specific question. 🤔 Please try rephrasing, or contact our team directly " +
                "via the Contact form and we'll assist you within 24 hours. You can also ask about: " +
                "registration, services, eligibility, volunteering, or emergency support.",
                "GENERAL",
                false
        );
    }

    // ── Auto-Response Generator (AI Feature) ──────────────────
    public String generateAutoResponse(String subject, String message) {
        String lowerSubject = subject.toLowerCase();
        String lowerMessage = message.toLowerCase();

        if (lowerSubject.contains("urgent") || lowerMessage.contains("emergency") || lowerMessage.contains("critical")) {
            return "Dear Patient/Volunteer,\n\nThank you for reaching out to Jarurat Care. " +
                   "We noticed your message is marked as urgent. Our team has been notified " +
                   "and will prioritize your case. Expected response: within 2 hours.\n\n" +
                   "If this is a medical emergency, please call 112 immediately.\n\nWith Care,\nJarurat Care Team";
        }

        if (lowerSubject.contains("volunteer") || lowerMessage.contains("volunteer")) {
            return "Dear Volunteer,\n\nThank you for your willingness to contribute to Jarurat Care! " +
                   "Your support makes a real difference in people's lives. " +
                   "Our volunteer coordinator will review your message and get back to you within 24–48 hours.\n\n" +
                   "With gratitude,\nJarurat Care Team";
        }

        return "Dear " + "Friend" + ",\n\nThank you for contacting Jarurat Care. " +
               "We have received your message and our team will respond within 24 hours. " +
               "Your health and well-being is our priority.\n\nWith Care,\nJarurat Care Team";
    }

    // ── Keyword scoring ────────────────────────────────────────
    private int calculateScore(String text, List<String> keywords) {
        int score = 0;
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                // Longer keyword match = higher score
                score += keyword.split("\\s+").length * 2;
            }
        }
        return score;
    }

    private String getDefaultResponse() {
        return "Hello! I'm the Jarurat Care assistant. Please ask me a question about our services, " +
               "registration, volunteering, or health support options.";
    }

    // ── Internal FAQ Model ─────────────────────────────────────
    private record FAQEntry(List<String> keywords, String category, String answer) {}
}
