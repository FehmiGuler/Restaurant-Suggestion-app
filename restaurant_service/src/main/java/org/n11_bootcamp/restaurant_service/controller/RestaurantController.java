package org.n11_bootcamp.restaurant_service.controller;

import org.n11_bootcamp.restaurant_service.controller.contract.RestaurantControllerContract;
import org.n11_bootcamp.restaurant_service.dto.RestaurantDTO;
import org.n11_bootcamp.restaurant_service.entity.Restaurant;
import org.n11_bootcamp.restaurant_service.request.RestaurantSaveRequest;
import org.n11_bootcamp.restaurant_service.request.RestaurantUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {

    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }

    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {

        Iterable<Restaurant> iterableAllRestaurants = restaurantControllerContract.getAllRestaurants();

        List<Restaurant> allRestaurants = new ArrayList<>();

        iterableAllRestaurants.forEach(allRestaurants::add);

        return allRestaurants;
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable String id) {

        Optional<Restaurant> restaurant = restaurantControllerContract.getRestaurantById(id);

        return restaurant;
    }

    @PostMapping
    public Restaurant save(@RequestBody RestaurantSaveRequest request) {

        Restaurant savedRestaurant = restaurantControllerContract.save(request);

        return savedRestaurant;
    }

    @PutMapping
    public Restaurant update(@RequestBody RestaurantUpdateRequest request) {
        Restaurant updatedRestaurant = restaurantControllerContract.update(request);
        return updatedRestaurant;
    }

    @DeleteMapping
    public void delete(@RequestParam String id) {
        restaurantControllerContract.delete(id);
    }

    @GetMapping("/nearby")
    public List<RestaurantDTO> getNearbyRestaurants(@RequestParam Double latitude,
                                                    @RequestParam Double longitude,
                                                    @RequestParam Double distanceInKm) {

        List<RestaurantDTO> restaurantSuggestDTOList = restaurantControllerContract.getNearbyRestaurants(latitude, longitude, distanceInKm);

        return restaurantSuggestDTOList;
    }
}
