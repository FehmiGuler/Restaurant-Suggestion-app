package org.n11_bootcamp.restaurant_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11_bootcamp.restaurant_service.dto.RestaurantDTO;
import org.n11_bootcamp.restaurant_service.entity.Restaurant;
import org.n11_bootcamp.restaurant_service.request.RestaurantSaveRequest;
import org.n11_bootcamp.restaurant_service.request.RestaurantUpdateRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "id", ignore = true)
    void updateRestaurantFields(@MappingTarget Restaurant restaurant, RestaurantUpdateRequest request);

    Restaurant convertToRestaurant(RestaurantSaveRequest request);

}
