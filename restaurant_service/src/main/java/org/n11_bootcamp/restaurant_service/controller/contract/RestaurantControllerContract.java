package org.n11_bootcamp.restaurant_service.controller.contract;

import org.n11_bootcamp.restaurant_service.dto.RestaurantDTO;
import org.n11_bootcamp.restaurant_service.entity.Restaurant;
import org.n11_bootcamp.restaurant_service.request.RestaurantSaveRequest;
import org.n11_bootcamp.restaurant_service.request.RestaurantUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface RestaurantControllerContract {
    Iterable<Restaurant> getAllRestaurants();

    Optional<Restaurant> getRestaurantById(String id);

    Restaurant save(RestaurantSaveRequest request);

    Restaurant update(RestaurantUpdateRequest request);

    void delete(String id);

    List<RestaurantDTO> getNearbyRestaurants(Double latitude,
                                             Double longitude,
                                             Double distanceInKm);
}
