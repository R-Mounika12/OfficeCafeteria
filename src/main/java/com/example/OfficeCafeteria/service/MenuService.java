package com.example.OfficeCafeteria.service;

import com.example.OfficeCafeteria.dto.MenuItemDto;
import com.example.OfficeCafeteria.dto.MenuRequest;
import com.example.OfficeCafeteria.dto.MenuResponse;
import com.example.OfficeCafeteria.dto.RestaurantDto;
import com.example.OfficeCafeteria.entity.Menu;
import com.example.OfficeCafeteria.exception.ResourceNotFoundException;
import com.example.OfficeCafeteria.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = Menu.builder()
                .restaurantId(menuRequest.getRestaurantId())
                .date(LocalDate.now())
                .mealType(menuRequest.getMealType())
                .menuItemIds(menuRequest.getMenuItemIds())
                .build();
        menuRepository.save(menu);
        return getMenuResponse(menu);
    }

    public MenuResponse getMenuById(Long id) {
        Menu menu = menuRepository.findById(id);
        return getMenuResponse(menu);
    }

    public List<MenuResponse> findAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(this::getMenuResponse).toList();
    }

    public MenuResponse updateMenu(MenuRequest menuRequest, Long id) {
        Menu menu = menuRepository.findById(id);
        if(menu != null) {
            menu.setRestaurantId(menuRequest.getRestaurantId());
            menu.setDate(LocalDate.now());
            menu.setMealType(menuRequest.getMealType());
            menu.setMenuItemIds(menuRequest.getMenuItemIds());
            menuRepository.save(menu);
            return getMenuResponse(menu);
        } else {
            throw new ResourceNotFoundException("Menu not found");
        }
    }

    public void deleteMenu(Long id) {
         menuRepository.deleteById(id);
    }

    public List<MenuResponse> getMenusByRestaurantId(Long restaurantId) {
        List<Menu> menuResponses = menuRepository.findMenuByRestaurantId(restaurantId);
        return menuResponses.stream().map(this::getMenuResponse).toList();
    }

    public List<MenuResponse> getMenusByRestaurantIdAndDate(Long restaurantId, LocalDate date) {
        List<Menu> menus = menuRepository.findByRestaurantIdAndDate(restaurantId, date);
        return menus.stream().map(this::getMenuResponse).toList();
    }

    public List<MenuResponse> getMenusByDate(LocalDate date) {
        List<Menu> menus = menuRepository.findByDate(date);
        return menus.stream().map(this::getMenuResponse).toList();
    }

    public MenuResponse updateMenuResponse(Long id, MenuRequest menuRequest) {
        Menu menu = menuRepository.findById(id);
        if(menu == null) {
            throw new ResourceNotFoundException("Menu not found with id: " + id);
        }
        
        menu.setRestaurantId(menuRequest.getRestaurantId());
        menu.setDate(LocalDate.now());
        menu.setMealType(menuRequest.getMealType());
        menu.setMenuItemIds(menuRequest.getMenuItemIds());
        menuRepository.save(menu);
        
       return getMenuResponse(menu);
    }
    
    public void deleteMenuResponse(Long id) {
        Menu menu = menuRepository.findById(id);
        if(menu == null) {
            throw new ResourceNotFoundException("Menu not found with id: " + id);
        }
        menuRepository.deleteById(id);
    }

    private MenuResponse getMenuResponse(Menu menu) {
        RestaurantDto restaurantDto = restaurantService.findRestaurantById(menu.getRestaurantId());
        List<MenuItemDto> menuItems = menuItemService.findMenuItemsByIds(menu.getMenuItemIds());
        return MenuResponse.builder()
                .id(menu.getId())
                .restaurantName(restaurantDto.getName())
                .restaurantLocation(restaurantDto.getLocation())
                .date(menu.getDate())
                .mealType(menu.getMealType())
                .items(menuItems)
                .build();
    }

}
