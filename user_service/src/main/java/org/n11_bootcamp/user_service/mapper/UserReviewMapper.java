package org.n11_bootcamp.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11_bootcamp.user_service.dto.UserReviewDTO;
import org.n11_bootcamp.user_service.entity.UserReview;
import org.n11_bootcamp.user_service.request.UserReviewSaveRequest;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReviewMapper {
    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "restaurantId", target = "restaurantId")
    UserReview convertToUserReview(UserReviewSaveRequest request);

    @Mapping(source = "user.id", target = "userId")
    UserReviewDTO convertToUserReviewDTO(UserReview userReview);

    // @Mapping(source = "user.id", target = "userId")
    List<UserReviewDTO> convertToUserReviewDTOList(List<UserReview> userReviews);

}
