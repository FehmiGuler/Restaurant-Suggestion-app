package org.n11_bootcamp.user_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.n11_bootcamp.user_service.enums.EnumRate;

public record UserReviewEditRateRequest(@NotNull @Positive Long id,
                                        @NotBlank EnumRate rate) {
}
