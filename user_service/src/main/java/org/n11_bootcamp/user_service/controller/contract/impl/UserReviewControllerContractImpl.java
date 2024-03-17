package org.n11_bootcamp.user_service.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11_bootcamp.user_service.controller.contract.UserReviewControllerContract;
import org.n11_bootcamp.user_service.dto.UserReviewDTO;
import org.n11_bootcamp.user_service.entity.UserReview;
import org.n11_bootcamp.user_service.mapper.UserReviewMapper;
import org.n11_bootcamp.user_service.request.UserReviewEditCommentRequest;
import org.n11_bootcamp.user_service.request.UserReviewEditRateRequest;
import org.n11_bootcamp.user_service.request.UserReviewSaveRequest;
import org.n11_bootcamp.user_service.service.UserReviewService;
import org.n11_bootcamp.user_service.service.entityservice.UserReviewEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserReviewControllerContractImpl implements UserReviewControllerContract {
    private final UserReviewEntityService userReviewEntityService;
    private final UserReviewService userReviewService;


    @Override
    public List<UserReviewDTO> getAllUserReviewsByUserId(Long userId) {

        List<UserReview> userReviewList = userReviewEntityService.findAllByUserId(userId);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTOList(userReviewList);
    }

    @Override
    public List<UserReviewDTO> getAllUserReviewsByRestaurantId(String restaurantId) {

        List<UserReview> userReviewList = userReviewEntityService.findAllByRestaurantId(restaurantId);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTOList(userReviewList);
    }

    @Override
    public UserReviewDTO saveUserReview(UserReviewSaveRequest request) {

        UserReview userReview = UserReviewMapper.INSTANCE.convertToUserReview(request);

        userReview = userReviewEntityService.save(userReview);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public UserReviewDTO editComment(Long id, UserReviewEditCommentRequest request) {

        UserReview userReview = userReviewEntityService.findByIdWithControl(id);
        userReview.setComment(request.comment());

        userReview = userReviewEntityService.save(userReview);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public UserReviewDTO editRate(Long id, UserReviewEditRateRequest request) {

        UserReview userReview = userReviewEntityService.findByIdWithControl(id);
        userReview.setRate(request.rate());

        userReview = userReviewEntityService.save(userReview);

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public void deleteUserReview(Long id) {
        userReviewEntityService.delete(id);
    }
}
