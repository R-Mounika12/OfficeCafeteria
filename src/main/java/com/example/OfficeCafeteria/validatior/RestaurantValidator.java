package com.example.OfficeCafeteria.validatior;

import com.example.OfficeCafeteria.dto.RestaurantDto;
import com.example.OfficeCafeteria.entity.Restaurant;
import com.example.OfficeCafeteria.exception.DuplicateResourceException;
import com.example.OfficeCafeteria.exception.InvalidRequestException;
import com.example.OfficeCafeteria.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestaurantValidator {

    private RestaurantRepository restaurantRepository;

    public void validateRestaurantDto(RestaurantDto restaurantDto) {
        if(restaurantDto.getName() == null || restaurantDto.getName().isEmpty()) {
            throw new InvalidRequestException("Restaurant name cannot be null or empty");
        }

        if(restaurantDto.getLocation() == null || restaurantDto.getLocation().isEmpty()) {
            throw new InvalidRequestException("Restaurant location cannot be null or empty");
        }

        Restaurant existingRestaurant = restaurantRepository.findByName(restaurantDto.getName());
        if(existingRestaurant != null && existingRestaurant.getLocation().equals(restaurantDto.getLocation())){
            throw new DuplicateResourceException("Restaurant with name " + restaurantDto.getName() + " and location " + restaurantDto.getLocation() + " already exists.");
        }

    }

    public void validateRestaurantId(Long restaurantId) {
        if(restaurantId == null || restaurantId == 0) {
            throw new InvalidRequestException("Restaurant Id cannot be null or empty");
        }
    }
}
