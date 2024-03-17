package org.n11_bootcamp.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11_bootcamp.user_service.dto.UserDTO;
import org.n11_bootcamp.user_service.entity.User;
import org.n11_bootcamp.user_service.request.UserSaveRequest;
import org.n11_bootcamp.user_service.request.UserUpdateRequest;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "status", constant = "PASSIVE")
    User convertToUser(UserSaveRequest request);

    UserDTO convertToUserDTO(User user);

    List<UserDTO> convertToUserDTOs(List<User> users);

    @Mapping(target = "id", ignore = true)
    void updateUserFields(@MappingTarget User user, UserUpdateRequest request);
}
