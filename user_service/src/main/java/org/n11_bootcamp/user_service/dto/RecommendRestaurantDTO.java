package org.n11_bootcamp.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRestaurantDTO {
    private String name;
    private Double latitude;
    private Double longitude;
    private Double distance;
    private Double totalScore;
    private String aiMessage;
}
