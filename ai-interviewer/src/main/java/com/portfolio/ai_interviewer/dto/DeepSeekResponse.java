package com.portfolio.ai_interviewer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeepSeekResponse {
    private List<Choice> choices;

    public String getContent() {
        if (choices != null && !choices.isEmpty()) {
            return choices.get(0).message.content;
        }
        return "Error: No response from DeepSeek.";
    }

    public void setChoices(List<Choice> choices) { this.choices = choices; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private Message message;
        public void setMessage(Message message) { this.message = message; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Message {
        private String content;
        public void setContent(String content) { this.content = content; }
    }
}