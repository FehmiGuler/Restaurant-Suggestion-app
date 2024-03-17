package org.n11_bootcamp.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NearbyRestaurantDTO {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double distance;
}
