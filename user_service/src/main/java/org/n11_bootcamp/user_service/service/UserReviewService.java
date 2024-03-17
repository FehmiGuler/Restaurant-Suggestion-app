package org.n11_bootcamp.user_service.service;

import org.n11_bootcamp.user_service.entity.UserReview;
import org.n11_bootcamp.user_service.service.entityservice.UserReviewEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewService {
    private final UserReviewEntityService userReviewEntityService;

    public UserReviewService(UserReviewEntityService userReviewEntityService) {
        this.userReviewEntityService = userReviewEntityService;
    }

    public UserReview saveUserReview(UserReview userReview) {
        userReview.setId((3L));
        return userReview;
    }

    public Double getUserReviewMeanValueByRestaurantId(String restaurantId) {

        List<UserReview> userReviewList = userReviewEntityService.findAllByRestaurantId(restaurantId);

        Double userReviewMeanValue = 0D;
        Integer userReviewTotal = 0;
        Integer userReviewCount = 0;

        for (UserReview userReview : userReviewList) {
            userReviewTotal = userReviewTotal + userReview.getRate().getValue();
            userReviewCount++;
        }

        if (userReviewTotal != 0) {
            userReviewMeanValue = (double) userReviewTotal / userReviewCount;
        }

        return userReviewMeanValue;
    }
}
