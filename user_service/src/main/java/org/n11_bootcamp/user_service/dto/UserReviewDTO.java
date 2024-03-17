package org.n11_bootcamp.user_service.dto;

import org.n11_bootcamp.user_service.enums.EnumRate;

public record UserReviewDTO(Long id,
                            Long userId,
                            String restaurantId,
                            Long orderId,
                            EnumRate rate,
                            String comment) {
}
