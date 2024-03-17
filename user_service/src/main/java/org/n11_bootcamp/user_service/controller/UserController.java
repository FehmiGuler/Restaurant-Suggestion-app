package org.n11_bootcamp.user_service.controller;

import org.n11_bootcamp.user_service.controller.contract.UserControllerContract;
import org.n11_bootcamp.user_service.dto.RecommendRestaurantDTO;
import org.n11_bootcamp.user_service.dto.UserDTO;
import org.n11_bootcamp.user_service.general.RestResponse;
import org.n11_bootcamp.user_service.request.UserSaveRequest;
import org.n11_bootcamp.user_service.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> getAllUsers() {
        List<UserDTO> allUsers = userControllerContract.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(allUsers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO userById = userControllerContract.getUserById(id);
        return ResponseEntity.ok(RestResponse.of(userById));
    }

    @GetMapping("/with-name/{name}")
    public ResponseEntity<RestResponse<UserDTO>> getUserByName(@PathVariable String name) {
        UserDTO userById = userControllerContract.getUserByName(name);
        return ResponseEntity.ok(RestResponse.of(userById));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@RequestBody UserSaveRequest request) {
        UserDTO userDTO = userControllerContract.saveUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @PutMapping("/{debugUserId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable Long debugUserId,
                                                            @RequestBody UserUpdateRequest request) {
        UserDTO userDTO = userControllerContract.updateUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userControllerContract.deleteUser(id);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<RestResponse<UserDTO>> activateUser(@PathVariable Long id) {
        UserDTO userDTO = userControllerContract.activateUser(id);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @GetMapping("/{id}/recommend-restaurant")
    public ResponseEntity<RestResponse<List<RecommendRestaurantDTO>>> recommendRestaurantById(@PathVariable Long id, @RequestParam Double distance) {

        List<RecommendRestaurantDTO> recommendRestaurantDTOList = userControllerContract.recommendRestaurantById(id, distance);

        return ResponseEntity.ok(RestResponse.of(recommendRestaurantDTOList));
    }

}
