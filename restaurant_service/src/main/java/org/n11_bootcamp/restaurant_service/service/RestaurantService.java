package org.n11_bootcamp.restaurant_service.service;

import org.n11_bootcamp.restaurant_service.dao.RestaurantRepository;
import org.n11_bootcamp.restaurant_service.dto.RestaurantDTO;
import org.n11_bootcamp.restaurant_service.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    // Method to calculate distance between two points using Haversine formula
    // The Haversine formula is a mathematical formula used to calculate the shortest
    // distance between two points on the surface of a sphere, given their longitudes and latitudes.
    // It's commonly used in navigation and geographic applications to compute distances
    // between locations on the Earth's surface.
    private Double calculateDistance(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
        final int R = 6371; // Radius of the Earth in kilometers
        Double lat1 = Math.toRadians(latitude1);
        Double lon1 = Math.toRadians(longitude1);
        Double lat2 = Math.toRadians(latitude2);
        Double lon2 = Math.toRadians(longitude2);

        Double dLat = lat2 - lat1;
        Double dLon = lon2 - lon1;

        Double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;

        return distance;
    }


    public List<RestaurantDTO> findNearbyRestaurants(Double latitude, Double longitude, Double distanceInKm) {

        Iterable<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantDTO> nearbyRestaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            Double distanceBetweenLocations = calculateDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude());

            if (distanceBetweenLocations < distanceInKm) {
                RestaurantDTO nearbyRestaurantDTO = new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getLatitude(), restaurant.getLongitude(), distanceBetweenLocations);
                nearbyRestaurants.add(nearbyRestaurantDTO);
            }
        }

        return nearbyRestaurants;
    }

}
