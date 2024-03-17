package org.n11_bootcamp.user_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserReviewEditCommentRequest(@NotNull @Positive Long id,
                                           @NotBlank String comment) {
}
