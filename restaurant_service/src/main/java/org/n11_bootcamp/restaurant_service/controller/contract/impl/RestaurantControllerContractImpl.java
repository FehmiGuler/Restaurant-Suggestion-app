package org.n11_bootcamp.restaurant_service.controller.contract.impl;


import org.n11_bootcamp.restaurant_service.controller.contract.RestaurantControllerContract;
import org.n11_bootcamp.restaurant_service.dao.RestaurantRepository;
import org.n11_bootcamp.restaurant_service.dto.RestaurantDTO;
import org.n11_bootcamp.restaurant_service.entity.Restaurant;
import org.n11_bootcamp.restaurant_service.mapper.RestaurantMapper;
import org.n11_bootcamp.restaurant_service.request.RestaurantSaveRequest;
import org.n11_bootcamp.restaurant_service.request.RestaurantUpdateRequest;
import org.n11_bootcamp.restaurant_service.service.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    public RestaurantControllerContractImpl(RestaurantService restaurantService,
                                            RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Iterable<Restaurant> getAllRestaurants() {

        Iterable<Restaurant> allRestaurants = restaurantRepository.findAll();

        return allRestaurants;
    }

    @Override
    public Optional<Restaurant> getRestaurantById(@PathVariable String id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        return restaurant;
    }

    @Override
    public Restaurant save(@RequestBody RestaurantSaveRequest request) {

        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(request);

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant;
    }

    @Override
    public Restaurant update(@RequestBody RestaurantUpdateRequest request) {

        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(request.id());

        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();

            RestaurantMapper.INSTANCE.updateRestaurantFields(restaurant, request);

            Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

            return updatedRestaurant;
        } else {
            throw new RuntimeException("Restaurant not found for ID: " + request.id());
        }
    }

    @Override
    public void delete(@PathVariable String id) {

        restaurantRepository.deleteById(id);
    }

    @Override
    public List<RestaurantDTO> getNearbyRestaurants(@RequestParam Double latitude,
                                                    @RequestParam Double longitude,
                                                    @RequestParam Double distanceInKm) {

        List<RestaurantDTO> nearbyRestaurants = restaurantService.findNearbyRestaurants(latitude, longitude, distanceInKm);

        return nearbyRestaurants;
    }
}
