# 🤖 AI Technical Interviewer

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![DeepSeek](https://img.shields.io/badge/DeepSeek-AI-4d53e8?style=for-the-badge&logo=openai&logoColor=white)

A full-stack application that simulates a real job interview using the **DeepSeek API**. This project leverages **DeepSeek's powerful reasoning models** (V3) to generate context-aware technical and behavioral questions, evaluate candidate answers, and provide constructive feedback in real-time with high accuracy.

---

## 📸 Screenshots

### 1. Choose Your Path

Select between a **Technical Interview** (Java, Spring Boot, Data Structures) or a **Behavioral Interview** (Soft skills, Conflict resolution).

<img width="702" height="760" alt="Welcome Screen" src="https://github.com/user-attachments/assets/211690b6-03c1-4c73-98cb-162730e3abc5" />

### 2. Deep Technical Questions

The AI challenges you with specific code scenarios, design patterns, and complexity analysis.

<img width="623" height="759" alt="Technical Question" src="https://github.com/user-attachments/assets/12a15bb6-cbc6-4185-aceb-0f51f0b9d524" />

### 3. Behavioral Situations

Practice your soft skills with situational judgement questions generated on the fly.

<img width="645" height="794" alt="Behavioral Question" src="https://github.com/user-attachments/assets/6323a3a7-754e-4100-b9c0-4c6d0d3a72d1" />

---

## 🚀 Key Features

- **🧠 Advanced Reasoning:** Powered by **DeepSeek API**, offering top-tier logic capabilities for evaluating complex code explanations.
- **⚡ Fast & Lightweight:** No need to run a heavy LLM locally; the application connects directly to the cloud API for instant responses.
- **🔄 Context-Aware:** The AI remembers the previous question to evaluate your answer accurately before moving to the next topic.
- **🎨 Modern UI:** A clean, dark-mode interface built with React and Tailwind CSS for a distraction-free experience.
- **🏗️ Robust Architecture:** Built using the Strategy Pattern in Spring Boot to easily extend interview types.

---

## 🛠️ Tech Stack

### Backend

- **Java 21**
- **Spring Boot 3** (Web, Data JPA)
- **Spring RestClient** (Communicating with DeepSeek API)
- **PostgreSQL** (Database)

### Frontend

- **React + Vite**
- **Tailwind CSS** (Styling)
- **Phosphor Icons**

### AI Engine

- **DeepSeek API** (Model: `deepseek-chat`)

---

## ⚙️ Getting Started

### Prerequisites

- Node.js (v18+)
- Java JDK 21
- A **DeepSeek API Key** (Get one at [platform.deepseek.com](https://platform.deepseek.com/))

### 1. Configure Backend & API Key

Navigate to `ai-interviewer/src/main/resources/application.properties` and add your key:

```properties
deepseek.api.key=sk-YOUR_API_KEY_HERE
```
