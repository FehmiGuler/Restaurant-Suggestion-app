package org.n11_bootcamp.ai_service.client;

import org.n11_bootcamp.ai_service.request.ChatCompletionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openai-client", url = "https://api.openai.com")
public interface OpenAiClient {
    @PostMapping(value = "/v1/chat/completions", consumes = MediaType.APPLICATION_JSON_VALUE)
    String generateMessage(@RequestBody ChatCompletionRequest requestBody, @RequestHeader("Authorization") String apiKey);
}
