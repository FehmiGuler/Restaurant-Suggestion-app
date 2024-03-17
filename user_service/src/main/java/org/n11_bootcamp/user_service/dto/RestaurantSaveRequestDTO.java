package org.n11_bootcamp.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RestaurantSaveRequestDTO(@NotBlank @Length(min = 1, max = 100) String name,
                                       @NotNull Double latitude,
                                       @NotNull Double longitude) {
}
