package com.example.OfficeCafeteria.controller;

import com.example.OfficeCafeteria.dto.MenuItemDto;
import com.example.OfficeCafeteria.entity.MenuItem;
import com.example.OfficeCafeteria.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemDto menuItemDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.createMenuItem(menuItemDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getMenuItem(@PathVariable Long id){
        return ResponseEntity.ok(menuItemService.getMenuItem(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllMenuItems(){
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto){
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, menuItemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
