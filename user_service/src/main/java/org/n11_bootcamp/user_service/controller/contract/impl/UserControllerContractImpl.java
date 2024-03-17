package org.n11_bootcamp.user_service.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11_bootcamp.user_service.client.RestaurantClient;
import org.n11_bootcamp.user_service.controller.contract.UserControllerContract;
import org.n11_bootcamp.user_service.dto.NearbyRestaurantDTO;
import org.n11_bootcamp.user_service.dto.RecommendRestaurantDTO;
import org.n11_bootcamp.user_service.dto.UserDTO;
import org.n11_bootcamp.user_service.entity.User;
import org.n11_bootcamp.user_service.enums.EnumStatus;
import org.n11_bootcamp.user_service.mapper.UserMapper;
import org.n11_bootcamp.user_service.request.UserSaveRequest;
import org.n11_bootcamp.user_service.request.UserUpdateRequest;
import org.n11_bootcamp.user_service.service.RecommendationService;
import org.n11_bootcamp.user_service.service.entityservice.UserEntityService;
import org.n11_bootcamp.user_service.service.entityservice.UserReviewEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;
    private final RecommendationService recommendationService;
    private final UserReviewEntityService userReviewEntityService;
    private final RestaurantClient restaurantClient;

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> userList = userEntityService.findAll();

        return UserMapper.INSTANCE.convertToUserDTOs(userList);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userEntityService.findByIdWithControl(id);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO getUserByName(String name) {
        User user = userEntityService.findUserByName(name);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO saveUser(UserSaveRequest request) {

        User user = UserMapper.INSTANCE.convertToUser(request);

        user = userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request) {

        User user = userEntityService.findByIdWithControl(request.id());
        UserMapper.INSTANCE.updateUserFields(user, request);

        user = userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userEntityService.delete(id);
    }

    @Override
    public UserDTO activateUser(Long id) {

        User user = userEntityService.findByIdWithControl(id);
        user.setStatus(EnumStatus.ACTIVE);

        user = userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public List<RecommendRestaurantDTO> recommendRestaurantById(Long id, Double distance) {

        User user = userEntityService.findByIdWithControl(id);

        List<NearbyRestaurantDTO> nearbyRestaurantDTOList = restaurantClient.getNearbyRestaurants(user.getLatitude(), user.getLongitude(), distance);

        return recommendationService.recommendRestaurant(user.getName(), nearbyRestaurantDTOList);
    }

}

