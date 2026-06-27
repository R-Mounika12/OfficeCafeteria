package com.example.OfficeCafeteria.service;

import com.example.OfficeCafeteria.dto.RestaurantDto;
import com.example.OfficeCafeteria.entity.Restaurant;
import com.example.OfficeCafeteria.exception.DuplicateResourceException;
import com.example.OfficeCafeteria.exception.ResourceNotFoundException;
import com.example.OfficeCafeteria.repository.RestaurantRepository;
import com.example.OfficeCafeteria.validatior.RestaurantValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantValidator restaurantValidator;


    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        restaurantValidator.validateRestaurantDto(restaurantDto);
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .location(restaurantDto.getLocation())
                .build();
        restaurantRepository.save(restaurant);
        return restaurantDto;
    }

    public RestaurantDto findRestaurantById(Long id) {
        restaurantValidator.validateRestaurantId(id);
            Restaurant restaurant =restaurantRepository.findById(id);
            if(null != restaurant) {
                return RestaurantDto.builder()
                        .name(restaurant.getName())
                        .location(restaurant.getLocation())
                        .build();
            }else {
                throw new ResourceNotFoundException("Restaurant not found");
            }

    }

    public List<RestaurantDto> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> RestaurantDto.builder()
                        .name(restaurant.getName())
                        .location(restaurant.getLocation())
                        .build()).toList();
    }

    public RestaurantDto updateRestaurant(Long id, RestaurantDto restaurantDto) {
        restaurantValidator.validateRestaurantId(id);
        restaurantValidator.validateRestaurantDto(restaurantDto);
        Restaurant existingRestaurant = restaurantRepository.findById(id);
           if(null != existingRestaurant) {
               Restaurant restaurantRequest = Restaurant.builder()
                       .name(restaurantDto.getName())
                       .location(restaurantDto.getLocation())
                       .build();
               restaurantRepository.update(id, restaurantRequest);
               return restaurantDto;
           }else{
               throw new ResourceNotFoundException("Restaurant not found");
           }

    }

    public void deleteRestaurant(Long id) {
        restaurantValidator.validateRestaurantId(id);
        restaurantRepository.delete(id);
    }
}
