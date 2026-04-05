package com.jarurat.healthcare.service;

import com.jarurat.healthcare.model.ContactMessage;
import com.jarurat.healthcare.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ChatbotService chatbotService;

    public ContactMessage save(ContactMessage message) {
        // AI FEATURE: Auto-generate a suggested response
        String autoResponse = chatbotService.generateAutoResponse(
                message.getSubject(), message.getMessage()
        );
        message.setAutoResponse(autoResponse);
        return contactRepository.save(message);
    }

    public List<ContactMessage> findAll() {
        return contactRepository.findAll();
    }

    public long countUnread() {
        return contactRepository.countByStatus("UNREAD");
    }

    public long count() {
        return contactRepository.count();
    }
}
