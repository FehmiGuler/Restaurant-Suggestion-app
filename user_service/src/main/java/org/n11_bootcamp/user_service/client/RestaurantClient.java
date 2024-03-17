package org.n11_bootcamp.user_service.client;

import org.n11_bootcamp.user_service.dto.NearbyRestaurantDTO;
import org.n11_bootcamp.user_service.dto.RestaurantDTO;
import org.n11_bootcamp.user_service.dto.RestaurantSaveRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "restaurant", url = "http://localhost:8081/api/v1/restaurants")
public interface RestaurantClient {
    @GetMapping
    Iterable<RestaurantDTO> getAllRestaurants();

    @GetMapping("/{id}")
    Optional<RestaurantDTO> getRestaurantById(@PathVariable String id);

    @PostMapping
    RestaurantDTO save(@RequestBody RestaurantSaveRequestDTO restaurantSaveRequestDTO);

    @GetMapping("/nearby")
    List<NearbyRestaurantDTO> getNearbyRestaurants(@RequestParam Double latitude,
                                                   @RequestParam Double longitude,
                                                   @RequestParam Double distanceInKm);

}
