# 🏥 Jarurat Care – Mini Healthcare Support Web App

**Internship Assignment** | Full Stack Developer (AI-Enabled) | Jarurat Care NGO

---

## 📌 Project Overview

A full-stack healthcare support web application built for the Jarurat Care NGO internship assignment. It allows patients to register for free healthcare support, enables volunteers to sign up, and provides a contact form with an **AI-powered chatbot** and **auto-response feature**.

---

## 🛠️ Tech Stack

| Layer       | Technology                        |
|-------------|-----------------------------------|
| Backend     | Java 17, Spring Boot 3.2          |
| ORM         | Spring Data JPA + Hibernate       |
| Database    | H2 In-Memory (dev) / MySQL (prod) |
| Frontend    | Thymeleaf, HTML5, CSS3, JS        |
| Validation  | Jakarta Bean Validation           |
| Build Tool  | Maven                             |
| Hosting     | Vercel / Render / Railway         |

---

## 🤖 AI Feature: FAQ Chatbot + Auto-Response

### 1. FAQ Chatbot (`/api/chatbot/ask`)
- **Rule-based NLP engine** with keyword scoring and intent classification
- Supports 12+ intent categories: `REGISTRATION`, `SERVICES`, `ELIGIBILITY`, `EMERGENCY`, `VOLUNTEER`, `GENERAL`
- Multilingual greeting support (English + Hindi)
- Embedded as a floating widget on every page
- REST endpoint: `POST /api/chatbot/ask` → `{ "question": "..." }`

### 2. Auto-Response Generator
- When a contact form is submitted, the `ChatbotService` **automatically generates a personalized response** based on subject/message content
- Handles: URGENT cases, volunteer inquiries, general queries
- Response is stored in DB alongside the message and displayed in the admin dashboard

---

## 📂 Project Structure

```
src/
└── main/
    ├── java/com/jarurat/healthcare/
    │   ├── HealthcareApplication.java       # Entry point
    │   ├── controller/
    │   │   ├── HomeController.java          # Landing page + Dashboard
    │   │   ├── PatientController.java       # Patient registration
    │   │   ├── VolunteerController.java     # Volunteer registration
    │   │   ├── ContactController.java       # Contact form
    │   │   └── ChatbotController.java       # REST API for AI chatbot
    │   ├── model/
    │   │   ├── Patient.java
    │   │   ├── Volunteer.java
    │   │   └── ContactMessage.java
    │   ├── repository/
    │   │   ├── PatientRepository.java
    │   │   ├── VolunteerRepository.java
    │   │   └── ContactRepository.java
    │   ├── service/
    │   │   ├── PatientService.java
    │   │   ├── VolunteerService.java
    │   │   ├── ContactService.java
    │   │   └── ChatbotService.java          # ⭐ AI Engine
    │   └── dto/
    │       └── ChatbotResponse.java
    └── resources/
        ├── application.properties
        ├── static/
        │   ├── css/style.css
        │   └── js/chatbot.js
        └── templates/
            ├── index.html                   # Landing page
            ├── patient-form.html            # Patient registration
            ├── volunteer-form.html          # Volunteer registration
            ├── contact.html                 # Contact + AI auto-response
            └── dashboard.html              # Admin data summary
```

---

## 🚀 How to Run Locally

### Prerequisites
- Java 17+
- Maven 3.8+

### Steps

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/healthcare-support-app.git
cd healthcare-support-app

# Run the application
./mvnw spring-boot:run

# OR build and run the JAR
./mvnw clean package
java -jar target/healthcare-support-app-1.0.0.jar
```

Open: [http://localhost:8080](http://localhost:8080)

H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:healthcaredb`
- Username: `sa` | Password: *(blank)*

---

## 📄 Pages & Routes

| Route                   | Description                          |
|-------------------------|--------------------------------------|
| `GET /`                 | Landing page with services overview  |
| `GET /patient/register` | Patient registration form            |
| `POST /patient/register`| Submit patient registration          |
| `GET /volunteer/register`| Volunteer registration form         |
| `POST /volunteer/register`| Submit volunteer registration      |
| `GET /contact`          | Contact form                         |
| `POST /contact`         | Submit contact message (AI responds) |
| `GET /dashboard`        | Admin dashboard with data summary    |
| `POST /api/chatbot/ask` | **AI Chatbot REST API**              |
| `GET /h2-console`       | H2 database console                  |

---

## 🎯 NGO Use Case

**Jarurat Care** is an NGO focused on making healthcare accessible to underprivileged Indians. This app:

1. **Reduces manual workload** by auto-classifying patient support types
2. **Scales outreach** by enabling self-registration 24/7
3. **Provides instant AI assistance** via the FAQ chatbot — no human required for common queries
4. **Tracks data** through the admin dashboard for reporting and volunteer coordination
5. **Auto-generates responses** saving the support team time on repetitive messages

---

## 👩‍💻 Developer

Built for the **Jarurat Care Full Stack Developer (AI-Enabled) Internship** assignment.

- Submission Deadline: 05 April, 2026
- Evaluators: Priyanka Joshi / Misty B

> *"We evaluate clarity and effort, not perfection."* – Jarurat Care Team

---

## 📃 License

MIT – Free to use and modify for NGO purposes.
