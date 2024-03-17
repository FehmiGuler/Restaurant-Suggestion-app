package org.n11_bootcamp.ai_service.request;

import org.n11_bootcamp.ai_service.entity.ChatCompletionMessage;

import java.util.List;

public class ChatCompletionRequest {
    private String model;
    private List<ChatCompletionMessage> messages;

    public ChatCompletionRequest() {}

    public ChatCompletionRequest(String model, List<ChatCompletionMessage> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatCompletionMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatCompletionMessage> messages) {
        this.messages = messages;
    }
}
