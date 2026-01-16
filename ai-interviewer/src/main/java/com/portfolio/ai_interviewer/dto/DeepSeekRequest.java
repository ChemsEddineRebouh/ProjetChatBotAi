package com.portfolio.ai_interviewer.dto;

import java.util.List;

public class DeepSeekRequest {
    private String model = "deepseek-chat";
    private List<Message> messages;
    private double temperature = 0.7;

    public DeepSeekRequest(String content) {
        this.messages = List.of(new Message("user", content));
    }

    public String getModel() { return model; }
    public List<Message> getMessages() { return messages; }
    public double getTemperature() { return temperature; }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
        public String getRole() { return role; }
        public String getContent() { return content; }
    }
}