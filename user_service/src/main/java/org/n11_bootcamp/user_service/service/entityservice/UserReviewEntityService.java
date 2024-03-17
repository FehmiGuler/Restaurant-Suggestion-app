package org.n11_bootcamp.user_service.service.entityservice;

import org.n11_bootcamp.user_service.dao.UserReviewRepository;
import org.n11_bootcamp.user_service.entity.UserReview;
import org.n11_bootcamp.user_service.enums.EnumRate;
import org.n11_bootcamp.user_service.general.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewEntityService extends BaseEntityService<UserReview, UserReviewRepository> {
    protected UserReviewEntityService(UserReviewRepository repository) {
        super(repository);
    }

    public UserReview findUserReviewById(Long id) {
        return getRepository().findUserReviewById(id);
    }

    public List<UserReview> findAllByUserId(Long userId) {
        return getRepository().findAllByUserId(userId);
    }

    public List<UserReview> findAllByRestaurantId(String restaurantId) {
        return getRepository().findAllByRestaurantId(restaurantId);
    }

    public List<String> findLastThreeCommentsByRestaurantIdAndRateGreaterThanThree(String restaurantId) {
        return getRepository().findLastThreeCommentsByRestaurantIdAndRateGreaterThanThree(restaurantId, EnumRate.RATE_3);
    }
}
