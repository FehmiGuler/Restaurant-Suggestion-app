package org.n11_bootcamp.user_service.dto;

import org.n11_bootcamp.user_service.enums.EnumGender;
import org.n11_bootcamp.user_service.enums.EnumStatus;

import java.time.LocalDate;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      LocalDate birthDate,
                      String email,
                      Double latitude,
                      Double longitude,
                      EnumGender gender,
                      EnumStatus status
) {
}
