package org.n11_bootcamp.user_service.controller;

import org.n11_bootcamp.user_service.controller.contract.UserReviewControllerContract;
import org.n11_bootcamp.user_service.dto.UserReviewDTO;
import org.n11_bootcamp.user_service.general.RestResponse;
import org.n11_bootcamp.user_service.request.UserReviewEditCommentRequest;
import org.n11_bootcamp.user_service.request.UserReviewEditRateRequest;
import org.n11_bootcamp.user_service.request.UserReviewSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-reviews")
public class UserReviewController {
    private final UserReviewControllerContract userReviewControllerContract;

    public UserReviewController(UserReviewControllerContract userReviewControllerContract) {
        this.userReviewControllerContract = userReviewControllerContract;
    }


    @GetMapping("/with-user-id/{userId}")
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviewsByUserId(@PathVariable Long userId) {
        List<UserReviewDTO> allUserReviews = userReviewControllerContract.getAllUserReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(allUserReviews));
    }

    @GetMapping("/with-restaurant-id/{restaurantId}")
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviewsByRestaurantId(@PathVariable String restaurantId) {
        List<UserReviewDTO> allUserReviewsByRestaurantId = userReviewControllerContract.getAllUserReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(RestResponse.of(allUserReviewsByRestaurantId));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> saveUserReview(@RequestBody UserReviewSaveRequest request) {
        UserReviewDTO userReviewDTO = userReviewControllerContract.saveUserReview(request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @PatchMapping("/{id}/edit-comment")
    public ResponseEntity<RestResponse<UserReviewDTO>> editComment(@PathVariable Long id, @RequestBody UserReviewEditCommentRequest request) {
        UserReviewDTO userReviewDTO = userReviewControllerContract.editComment(id, request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @PatchMapping("/{id}/edit-rate")
    public ResponseEntity<RestResponse<UserReviewDTO>> editRate(@PathVariable Long id, @RequestBody UserReviewEditRateRequest request) {
        UserReviewDTO userReviewDTO = userReviewControllerContract.editRate(id, request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserReview(@PathVariable Long id) {
        userReviewControllerContract.deleteUserReview(id);
    }


}
