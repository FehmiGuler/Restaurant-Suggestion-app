package org.n11_bootcamp.user_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.n11_bootcamp.user_service.enums.EnumGender;

import java.time.LocalDate;

public record UserUpdateRequest(@NotNull @Positive Long id,
                                @NotBlank @Length(min = 1, max = 100) String name,
                                @NotBlank @Length(min = 1, max = 100) String surname,
                                @NotBlank @Past LocalDate birthDate,
                                @NotBlank @Length(min = 1, max = 100)String email,
                                @NotNull Double latitude,
                                @NotNull Double longitude,
                                @NotBlank EnumGender gender) {
}
