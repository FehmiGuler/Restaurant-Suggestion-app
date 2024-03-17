package org.n11_bootcamp.user_service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.n11_bootcamp.user_service.enums.EnumGender;

import java.time.LocalDate;

public record UserSaveRequest(@NotBlank @Length(min = 1, max = 100) String name,
                              @NotBlank @Length(min = 1, max = 100) String surname,
                              @NotNull @Past LocalDate birthDate,
                              @NotBlank @Length(min = 1, max = 100) String email,
                              @NotNull Double latitude,
                              @NotNull Double longitude,
                              @NotBlank EnumGender gender) {
}
