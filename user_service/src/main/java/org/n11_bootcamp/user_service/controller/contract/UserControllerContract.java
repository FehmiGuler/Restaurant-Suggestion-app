package org.n11_bootcamp.user_service.controller.contract;

import org.n11_bootcamp.user_service.dto.RecommendRestaurantDTO;
import org.n11_bootcamp.user_service.dto.UserDTO;
import org.n11_bootcamp.user_service.request.UserSaveRequest;
import org.n11_bootcamp.user_service.request.UserUpdateRequest;

import java.util.List;

public interface UserControllerContract {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByName(String name);

    UserDTO saveUser(UserSaveRequest request);

    UserDTO updateUser(UserUpdateRequest request);

    void deleteUser(Long id);

    UserDTO activateUser(Long id);

    List<RecommendRestaurantDTO> recommendRestaurantById(Long id, Double distance);

}
