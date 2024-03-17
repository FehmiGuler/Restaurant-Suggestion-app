package org.n11_bootcamp.ai_service.entity;

public class ChatCompletionMessage {
    private String role;
    private String content;

    public ChatCompletionMessage() {
    }

    public ChatCompletionMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
