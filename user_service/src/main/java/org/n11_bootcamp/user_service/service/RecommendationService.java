package org.n11_bootcamp.user_service.service;

import org.n11_bootcamp.user_service.client.RecommendationMessageClient;
import org.n11_bootcamp.user_service.dto.NearbyRestaurantDTO;
import org.n11_bootcamp.user_service.dto.RecommendRestaurantDTO;
import org.n11_bootcamp.user_service.service.entityservice.UserReviewEntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendationService {

    private final UserReviewService userReviewService;
    private final UserReviewEntityService userReviewEntityService;
    private final RecommendationMessageClient recommendationMessageClient;

    public RecommendationService(UserReviewService userReviewService, UserReviewEntityService userReviewEntityService, RecommendationMessageClient recommendationMessageClient) {
        this.userReviewService = userReviewService;
        this.userReviewEntityService = userReviewEntityService;
        this.recommendationMessageClient = recommendationMessageClient;
    }

    public Double getRestaurantScore(NearbyRestaurantDTO nearbyRestaurant) {

        Double userReviewMeanValue = userReviewService.getUserReviewMeanValueByRestaurantId(nearbyRestaurant.getId());

        Double reviewScore = userReviewMeanValue * 7D;
        Double distanceScore;
        if (nearbyRestaurant.getDistance() == 0D) {
            distanceScore = 3D;
        } else {
            distanceScore = 3D / nearbyRestaurant.getDistance();
        }

        return reviewScore + distanceScore;
    }

    public String getRecommendationMessage(String userName, String restaurantName, String restaurantId) {

        List<String> comments = userReviewEntityService.findLastThreeCommentsByRestaurantIdAndRateGreaterThanThree(restaurantId);

        return recommendationMessageClient.generateRecommendationMessage(userName, restaurantName, comments);
    }

    public List<RecommendRestaurantDTO> recommendRestaurant(String userName, List<NearbyRestaurantDTO> nearbyRestaurantDTOList) {

        List<RecommendRestaurantDTO> recommendedRestaurants = new ArrayList<>();
        Double minRelevancyScore = 0D;
        for (NearbyRestaurantDTO nearbyRestaurant : nearbyRestaurantDTOList) {

            Double restaurantRelevancyScore = getRestaurantScore(nearbyRestaurant);

            if (restaurantRelevancyScore < minRelevancyScore && recommendedRestaurants.size() >= 2) {
                continue;
            }

            String aiMessage = getRecommendationMessage(userName, nearbyRestaurant.getName(), nearbyRestaurant.getId());

            minRelevancyScore = restaurantRelevancyScore;

            recommendedRestaurants.add(
                    new RecommendRestaurantDTO(nearbyRestaurant.getName(),
                            nearbyRestaurant.getLatitude(),
                            nearbyRestaurant.getLongitude(),
                            nearbyRestaurant.getDistance(),
                            restaurantRelevancyScore,
                            aiMessage));
        }
        recommendedRestaurants.sort(Comparator.comparingDouble(RecommendRestaurantDTO::getTotalScore).reversed());
        return recommendedRestaurants;
    }
}
