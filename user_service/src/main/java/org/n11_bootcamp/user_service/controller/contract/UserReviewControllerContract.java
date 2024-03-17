package org.n11_bootcamp.user_service.controller.contract;

import org.n11_bootcamp.user_service.dto.UserReviewDTO;
import org.n11_bootcamp.user_service.request.UserReviewEditCommentRequest;
import org.n11_bootcamp.user_service.request.UserReviewEditRateRequest;
import org.n11_bootcamp.user_service.request.UserReviewSaveRequest;

import java.util.List;

public interface UserReviewControllerContract {

    List<UserReviewDTO> getAllUserReviewsByUserId(Long userId);

    List<UserReviewDTO> getAllUserReviewsByRestaurantId(String restaurantId);

    UserReviewDTO saveUserReview(UserReviewSaveRequest request);

    UserReviewDTO editComment(Long id, UserReviewEditCommentRequest request);

    UserReviewDTO editRate(Long id, UserReviewEditRateRequest request);

    void deleteUserReview(Long id);
}
