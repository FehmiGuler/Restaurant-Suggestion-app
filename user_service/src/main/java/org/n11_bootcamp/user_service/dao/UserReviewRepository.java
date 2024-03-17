package org.n11_bootcamp.user_service.dao;

import org.n11_bootcamp.user_service.entity.UserReview;
import org.n11_bootcamp.user_service.enums.EnumRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    UserReview findUserReviewById(Long id);

    List<UserReview> findAllByUserId(Long userId);

    List<UserReview> findAllByRestaurantId(String restaurantId);

    @Query(value = "SELECT ur.comment FROM UserReview ur WHERE ur.restaurantId = :restaurantId AND ur.rate > :rate ORDER BY ur.id DESC")
    List<String> findLastThreeCommentsByRestaurantIdAndRateGreaterThanThree(@Param("restaurantId") String restaurantId, @Param("rate") EnumRate rate);
}
