package com.example.OfficeCafeteria.repository;

import com.example.OfficeCafeteria.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RestaurantRepository {

    private Map<Long, Restaurant> restaurantMap = new HashMap<>();
    private static AtomicLong idCounter = new AtomicLong(1L);

    public Restaurant save(Restaurant restaurant) {
        if(restaurant.getId() == null)  {
            restaurant.setId(idCounter.getAndIncrement());
        }
        restaurantMap.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public Restaurant findById(Long id) {
        if(restaurantMap.containsKey(id)){
            return restaurantMap.get(id);
        }else{
            return null;
        }
    }

    public List<Restaurant> findAll() {
        return restaurantMap.values().stream().toList();
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        if(restaurantMap.containsKey(id)) {
            restaurantMap.put(id, restaurant);
            return restaurant;
        }else {
            return null;
        }
    }

    public void delete(Long id) {
        if(restaurantMap.containsKey(id)){
            restaurantMap.remove(id);
        }
    }

    public Restaurant findByName(String name) {
        return restaurantMap.values().stream()
                .filter(restaurant ->  restaurant.getName().equals(name))
                .findFirst().orElse(null);
    }
}
