package org.n11_bootcamp.restaurant_service.request;

public record RestaurantUpdateRequest(String id, String name, Double latitude, Double longitude) {
}
