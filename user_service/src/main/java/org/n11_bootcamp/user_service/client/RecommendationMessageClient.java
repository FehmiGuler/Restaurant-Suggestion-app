package org.n11_bootcamp.user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "recommendation-message", url = "http://localhost:8084/api/v1/recommendation-message")
public interface RecommendationMessageClient {
    @GetMapping()
    String generateRecommendationMessage(@RequestParam String username,
                                         @RequestParam String restaurantName,
                                         @RequestParam List<String> comments);
}
