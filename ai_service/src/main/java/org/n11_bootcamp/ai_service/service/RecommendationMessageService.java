package org.n11_bootcamp.ai_service.service;

import org.n11_bootcamp.ai_service.client.OpenAiClient;
import org.n11_bootcamp.ai_service.entity.ChatCompletionMessage;
import org.n11_bootcamp.ai_service.request.ChatCompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationMessageService {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.systemMessage}")
    private String systemMessage;

    @Autowired
    private OpenAiClient openAiClient;

    public String createPrompt(String username, String restaurantName, List<String> comments) {
        if (username == null || restaurantName == null || comments == null) {
            throw new IllegalArgumentException("Kullanıcı adı, restoran adı veya yorumlar null olamaz.");
        }
        String combinedComments = String.join("\n", comments);
        return String.format("user_to_recommend: %s, restaurant: %s, comments: %s", username, restaurantName, combinedComments);
    }

    public String generateMessage(String prompt) {
        try {
            List<ChatCompletionMessage> messages = new ArrayList<>();
            messages.add(new ChatCompletionMessage("system", systemMessage));
            messages.add(new ChatCompletionMessage("user", prompt));

            ChatCompletionRequest requestBody = new ChatCompletionRequest(model, messages);


            return extractMessageFromJSONResponse(openAiClient.generateMessage(requestBody, "Bearer " + openAiApiKey));
        } catch (Exception e) {
            throw new RuntimeException("OpenAI API'ye bağlanırken hata oluştu.", e);
        }
    }

    private String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

}
