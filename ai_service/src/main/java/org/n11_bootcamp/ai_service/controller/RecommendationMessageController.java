package org.n11_bootcamp.ai_service.controller;

import lombok.AllArgsConstructor;
import org.n11_bootcamp.ai_service.service.RecommendationMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/recommendation-message")
@AllArgsConstructor
public class RecommendationMessageController {

    private RecommendationMessageService recommendationMessageService;
    @GetMapping()
    public String generateRecommendationMessage(@RequestParam String username,
                                                @RequestParam String restaurantName,
                                                @RequestParam List<String> comments) {

        String prompt = recommendationMessageService.createPrompt(username, restaurantName, comments);

        return recommendationMessageService.generateMessage(prompt);
    }

}
