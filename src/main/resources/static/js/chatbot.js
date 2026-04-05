/**
 * Jarurat Care – AI FAQ Chatbot Frontend
 * Communicates with POST /api/chatbot/ask
 */

let chatbotOpen = false;

function toggleChatbot() {
    const widget = document.getElementById('chatbotWidget');
    const fab = document.getElementById('chatbotFab');
    const toggleIcon = document.getElementById('chatToggleIcon');
    chatbotOpen = !chatbotOpen;
    if (chatbotOpen) {
        widget.classList.add('open');
        if (fab) fab.style.display = 'none';
        if (toggleIcon) toggleIcon.textContent = '✕';
    } else {
        widget.classList.remove('open');
        if (fab) fab.style.display = 'flex';
    }
}

// Show chatbot open by default on load
window.addEventListener('DOMContentLoaded', () => {
    const widget = document.getElementById('chatbotWidget');
    const fab = document.getElementById('chatbotFab');
    // Start with fab visible, widget hidden
    if (widget) widget.classList.remove('open');
    if (fab) fab.style.display = 'flex';
});

function handleChatKey(event) {
    if (event.key === 'Enter') sendChatMessage();
}

function askQuestion(question) {
    document.getElementById('chatInput').value = question;
    sendChatMessage();
}

async function sendChatMessage() {
    const input = document.getElementById('chatInput');
    const question = input.value.trim();
    if (!question) return;

    appendMessage(question, 'user');
    input.value = '';

    const typingId = showTyping();

    try {
        const response = await fetch('/api/chatbot/ask', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ question })
        });

        if (!response.ok) throw new Error('Server error');
        const data = await response.json();

        removeTyping(typingId);
        appendMessage(data.answer, 'bot');

    } catch (err) {
        removeTyping(typingId);
        appendMessage('Sorry, I\'m having trouble connecting. Please try again or use our Contact form. 🙏', 'bot');
    }
}

function appendMessage(text, sender) {
    const container = document.getElementById('chatMessages');
    const msgDiv = document.createElement('div');
    msgDiv.className = `chat-msg ${sender}`;

    const avatar = document.createElement('span');
    avatar.className = 'chat-avatar';
    avatar.textContent = sender === 'bot' ? '🤖' : '👤';

    const bubble = document.createElement('div');
    bubble.className = 'chat-bubble';
    bubble.textContent = text;

    if (sender === 'user') {
        msgDiv.appendChild(bubble);
        msgDiv.appendChild(avatar);
    } else {
        msgDiv.appendChild(avatar);
        msgDiv.appendChild(bubble);
    }

    container.appendChild(msgDiv);
    container.scrollTop = container.scrollHeight;
}

function showTyping() {
    const container = document.getElementById('chatMessages');
    const id = 'typing-' + Date.now();
    const typingDiv = document.createElement('div');
    typingDiv.className = 'chat-msg bot typing-indicator';
    typingDiv.id = id;
    typingDiv.innerHTML = `
        <span class="chat-avatar">🤖</span>
        <div class="chat-bubble">
            <div class="typing-dot"></div>
            <div class="typing-dot"></div>
            <div class="typing-dot"></div>
        </div>`;
    container.appendChild(typingDiv);
    container.scrollTop = container.scrollHeight;
    return id;
}

function removeTyping(id) {
    const el = document.getElementById(id);
    if (el) el.remove();
}
