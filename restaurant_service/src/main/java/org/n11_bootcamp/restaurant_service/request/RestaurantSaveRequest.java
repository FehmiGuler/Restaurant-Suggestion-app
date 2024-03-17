package org.n11_bootcamp.restaurant_service.request;

public record RestaurantSaveRequest(String name, Double latitude, Double longitude) {
}
