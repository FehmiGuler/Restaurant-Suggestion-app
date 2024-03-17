package org.n11_bootcamp.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.n11_bootcamp.user_service.client.RestaurantClient;
import org.n11_bootcamp.user_service.dto.NearbyRestaurantDTO;
import org.n11_bootcamp.user_service.dto.RestaurantDTO;
import org.n11_bootcamp.user_service.dto.RestaurantSaveRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantClient restaurantClient;


    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        Iterable<RestaurantDTO> iterableAllRestaurants = restaurantClient.getAllRestaurants();

        List<RestaurantDTO> allRestaurants = new ArrayList<>();

        iterableAllRestaurants.forEach(allRestaurants::add);

        return allRestaurants;
    }

    @GetMapping("/{id}")
    public Optional<RestaurantDTO> getRestaurantById(@PathVariable String id) {

        return restaurantClient.getRestaurantById(id);
    }

    @PostMapping()
    public RestaurantDTO saveRestaurant(@RequestBody RestaurantSaveRequestDTO restaurantSaveRequestDTO) {

        return restaurantClient.save(restaurantSaveRequestDTO);
    }

    @GetMapping("/nearby")
    public List<NearbyRestaurantDTO> getNearbyRestaurants(@RequestParam Double latitude,
                                                          @RequestParam Double longitude,
                                                          @RequestParam Double distanceInKm) {

        return restaurantClient.getNearbyRestaurants(latitude, longitude, distanceInKm);
    }


}
