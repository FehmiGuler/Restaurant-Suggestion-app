package org.n11_bootcamp.restaurant_service.dao;

import org.n11_bootcamp.restaurant_service.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
    Optional<Restaurant> findById(String id);

}
