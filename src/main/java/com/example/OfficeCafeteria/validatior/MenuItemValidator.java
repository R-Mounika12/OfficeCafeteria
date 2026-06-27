package com.example.OfficeCafeteria.validatior;

import com.example.OfficeCafeteria.dto.MenuItemDto;
import com.example.OfficeCafeteria.entity.MenuItem;
import com.example.OfficeCafeteria.exception.DuplicateResourceException;
import com.example.OfficeCafeteria.exception.InvalidRequestException;
import com.example.OfficeCafeteria.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuItemValidator {

    private final MenuItemRepository menuItemRepository;

    public void validateMenuItemDto(MenuItemDto menuItem) {
      validateCreateMenuItemDto(menuItem);
        MenuItem existingMenuItem = menuItemRepository.findByName(menuItem.getName());
        if(existingMenuItem != null) {
            throw new DuplicateResourceException(menuItem.getName()+" already exists");
        }
    }

    public void validateMenuItemId(Long menuItemId) {
        if(menuItemId == null || menuItemId < 0) {
            throw new InvalidRequestException("Menu item id cannot be null or negative");
        }
    }

    public void validateCreateMenuItemDto(MenuItemDto menuItem) {
        if(menuItem.getName() == null || menuItem.getName().isEmpty()) {
            throw new InvalidRequestException("Menu item name cannot be null or empty");
        }
        if(menuItem.getPrice() == null || menuItem.getPrice() <= 0) {
            throw new InvalidRequestException("Menu item price cannot be null or negative");
        }
        if(menuItem.getCategory() == null){
            throw new InvalidRequestException("Menu item category cannot be null");
        }

    }

    public void validateUpdateMenuItemDto(MenuItemDto menuItemDto) {
        validateCreateMenuItemDto(menuItemDto);
    }
}
