package com.example.OfficeCafeteria.service;

import com.example.OfficeCafeteria.dto.MenuItemDto;
import com.example.OfficeCafeteria.entity.MenuItem;
import com.example.OfficeCafeteria.exception.ResourceNotFoundException;
import com.example.OfficeCafeteria.repository.MenuItemRepository;
import com.example.OfficeCafeteria.validatior.MenuItemValidator;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemValidator menuItemValidator;

    public MenuItemDto createMenuItem(MenuItemDto menuItemDto) {
        menuItemValidator.validateMenuItemDto(menuItemDto);
        MenuItem menuItem = MenuItem.builder()
                .name(menuItemDto.getName())
                .description(menuItemDto.getDescription())
                .price(menuItemDto.getPrice())
                .isVegetarian(menuItemDto.getIsVegetarian())
                .category(menuItemDto.getCategory())
                .build();
        menuItemRepository.save(menuItem);
        return menuItemDto;
    }

    public MenuItemDto getMenuItem(Long id) {
        menuItemValidator.validateMenuItemId(id);
        MenuItem menuItem = menuItemRepository.findById(id);
        if(menuItem == null) {
            throw new ResourceNotFoundException("Menu item not found");
        }
        return MenuItemDto.builder()
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .isVegetarian(menuItem.getIsVegetarian())
                .category(menuItem.getCategory())
                .build();
    }

    public List<MenuItemDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItem -> MenuItemDto.builder()
                        .name(menuItem.getName())
                        .description(menuItem.getDescription())
                        .price(menuItem.getPrice())
                        .isVegetarian(menuItem.getIsVegetarian())
                        .category(menuItem.getCategory())
                        .build()).toList();
    }

    public MenuItemDto updateMenuItem(Long id, MenuItemDto menuItemDto) {
        menuItemValidator.validateUpdateMenuItemDto(menuItemDto);
        MenuItem menuItem = menuItemRepository.findById(id);
        if(menuItem != null) {
            menuItem.setName(menuItemDto.getName());
            menuItem.setDescription(menuItemDto.getDescription());
            menuItem.setPrice(menuItemDto.getPrice());
            menuItem.setIsVegetarian(menuItemDto.getIsVegetarian());
            menuItem.setCategory(menuItemDto.getCategory());
            menuItemRepository.update(id, menuItem);
            return menuItemDto;
        } else {
            throw new ResourceNotFoundException("Menu item not found");
        }
    }

    public void deleteMenuItem(Long id) {
        menuItemValidator.validateMenuItemId(id);
        menuItemRepository.delete(id);
    }

    public List<MenuItemDto> findMenuItemsByIds(List<Long> ids) {
        return menuItemRepository.findMenuItemsByIds(ids).stream()
                .map(menuItem -> MenuItemDto.builder()
                        .name(menuItem.getName())
                        .description(menuItem.getDescription())
                        .price(menuItem.getPrice())
                        .isVegetarian(menuItem.getIsVegetarian())
                        .category(menuItem.getCategory())
                        .build()).toList();
    }
}
