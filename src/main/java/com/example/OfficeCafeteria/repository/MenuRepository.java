package com.example.OfficeCafeteria.repository;

import com.example.OfficeCafeteria.constants.MealType;
import com.example.OfficeCafeteria.entity.Menu;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MenuRepository {

    private Map<Long , Menu> menuMap = new HashMap<>();
    private static AtomicLong idCounter = new AtomicLong(1L);

    public Menu save(Menu menu) {
        if(menu.getId() == null) {
            menu.setId(idCounter.getAndIncrement());
        }
        menuMap.put(menu.getId(), menu);
        return menu;
    }

    public Menu findById(Long id) {
        return menuMap.get(id);
    }

    public List<Menu> findAll() {
        return menuMap.values().stream().toList();
    }

    public List<Menu> findMenuByRestaurantId(Long restaurantId) {
        return menuMap.values().stream()
                .filter(menu -> menu.getRestaurantId().equals(restaurantId))
                .toList();
    }

    public List<Menu> findByDate(LocalDate date) {
        return menuMap.values().stream()
                .filter(menu -> menu.getDate().equals(date))
                .toList();
    }

    public List<Menu> findByMealType(MealType mealType) {
        return menuMap.values().stream()
                .filter(menu -> menu.getMealType().equals(mealType))
                .toList();
    }

    public List<Menu> findByRestaurantIdAndDate(Long restaurantId, LocalDate date) {
        return menuMap.values().stream()
                .filter(menu -> menu.getRestaurantId().equals(restaurantId) && menu.getDate().equals(date))
                .toList();
    }

    public Optional<Menu> findByRestaurantIdAndDateAndMealType(Long restaurantId, LocalDate date, MealType mealType) {
        return menuMap.values().stream()
                .filter(menu -> menu.getRestaurantId().equals(restaurantId) && menu.getDate().equals(date) && menu.getMealType().equals(mealType))
                .findFirst();
    }

    public Boolean existsById(Long id) {
        return menuMap.containsKey(id);
    }

    public boolean deleteById(Long id) {
        return menuMap.remove(id) != null;
    }
}
