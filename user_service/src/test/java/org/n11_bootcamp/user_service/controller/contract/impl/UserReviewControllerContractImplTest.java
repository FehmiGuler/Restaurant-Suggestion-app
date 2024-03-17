package org.n11_bootcamp.user_service.controller.contract.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11_bootcamp.user_service.dto.UserReviewDTO;
import org.n11_bootcamp.user_service.entity.UserReview;
import org.n11_bootcamp.user_service.enums.EnumRate;
import org.n11_bootcamp.user_service.request.UserReviewEditCommentRequest;
import org.n11_bootcamp.user_service.request.UserReviewEditRateRequest;
import org.n11_bootcamp.user_service.request.UserReviewSaveRequest;
import org.n11_bootcamp.user_service.service.UserReviewService;
import org.n11_bootcamp.user_service.service.entityservice.UserReviewEntityService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserReviewControllerContractImplTest {

    @Mock
    private UserReviewEntityService userReviewEntityService;

    @InjectMocks
    private UserReviewControllerContractImpl userReviewControllerContractImpl;

    @Captor
    private ArgumentCaptor<UserReview> userReviewCaptor;

    @Test
    void shouldGetAllUserReviewsByUserId() {

        // given
        Long userId = 1L;
        List<UserReview> userReviewList = Collections.emptyList();
        List<UserReviewDTO> expectedUserReviewDetailDTOs = Collections.emptyList();

        // when
        Mockito.when(userReviewEntityService.findAllByUserId(userId)).thenReturn(userReviewList);
        List<UserReviewDTO> actualUserReviewDetailDTOs = userReviewControllerContractImpl.getAllUserReviewsByUserId(userId);

        // then
        assertEquals(expectedUserReviewDetailDTOs, actualUserReviewDetailDTOs);
    }

    @Test
    void shouldGetAllUserReviewsByRestaurantId() {
        // given
        String restaurantId = "1";
        List<UserReview> userReviewList = Collections.emptyList();
        List<UserReviewDTO> expectedUserReviewDetailDTOs = Collections.emptyList();

        // when
        Mockito.when(userReviewEntityService.findAllByRestaurantId(restaurantId)).thenReturn(userReviewList);
        List<UserReviewDTO> actualUserReviewDetailDTOs = userReviewControllerContractImpl.getAllUserReviewsByRestaurantId(restaurantId);

        // then
        assertEquals(expectedUserReviewDetailDTOs, actualUserReviewDetailDTOs);
    }

    @Test
    void shouldSaveUserReview() {
        // given
        Long id = 1L;
        Long userId = 2L;

        UserReviewSaveRequest request = Mockito.mock(UserReviewSaveRequest.class);
        UserReview userReview = Mockito.mock(UserReview.class);

        // when
        Mockito.when(userReview.getId()).thenReturn(id);

        Mockito.when(request.userId()).thenReturn(userId);
        Mockito.when(userReviewEntityService.save(Mockito.any())).thenReturn(userReview);

        UserReviewDTO result = userReviewControllerContractImpl.saveUserReview(request);

        Mockito.verify(userReviewEntityService).save(Mockito.any());

        // then
        assertEquals(id, result.id());
    }

    @Test
    void shouldEditComment() {

        // given
        Long id = 1L;
        String comment = "comment";
        String newComment = "new comment";

        UserReviewEditCommentRequest request = new UserReviewEditCommentRequest(id, newComment);

        UserReview userReview = new UserReview();
        userReview.setId(id);
        userReview.setComment(comment);

        //when
        Mockito.when(userReviewEntityService.findByIdWithControl(Mockito.anyLong())).thenReturn(userReview);

        UserReviewDTO result = userReviewControllerContractImpl.editComment(id, request);

        //then
        InOrder inOrder = Mockito.inOrder(userReviewEntityService);
        inOrder.verify(userReviewEntityService).findByIdWithControl(id);
        inOrder.verify(userReviewEntityService).save(userReviewCaptor.capture());
        inOrder.verifyNoMoreInteractions();

        UserReview userReviewCaptorValue = userReviewCaptor.getValue();

        assertEquals(newComment, userReviewCaptorValue.getComment());
        assertEquals(id, userReviewCaptorValue.getId());
    }

    @Test
    void shouldEditRate() {

        // given
        Long id = 1L;
        EnumRate rate = EnumRate.RATE_1;
        EnumRate newRate = EnumRate.RATE_4;

        UserReviewEditRateRequest request = new UserReviewEditRateRequest(id, newRate);

        UserReview userReview = new UserReview();
        userReview.setId(id);
        userReview.setRate(rate);

        //when
        Mockito.when(userReviewEntityService.findByIdWithControl(Mockito.anyLong())).thenReturn(userReview);
        UserReviewDTO result = userReviewControllerContractImpl.editRate(id, request);

        //then
        InOrder inOrder = Mockito.inOrder(userReviewEntityService);
        inOrder.verify(userReviewEntityService).findByIdWithControl(id);
        inOrder.verify(userReviewEntityService).save(userReviewCaptor.capture());
        inOrder.verifyNoMoreInteractions();

        UserReview userReviewCaptorValue = userReviewCaptor.getValue();

        assertEquals(newRate, userReviewCaptorValue.getRate());
        assertEquals(id, userReviewCaptorValue.getId());
    }
}