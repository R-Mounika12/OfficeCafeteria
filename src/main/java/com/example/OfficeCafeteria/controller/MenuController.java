package com.example.OfficeCafeteria.controller;

import com.example.OfficeCafeteria.dto.MenuRequest;
import com.example.OfficeCafeteria.dto.MenuResponse;
import com.example.OfficeCafeteria.service.MenuService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest menuRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(menuRequest));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getMenus() {
        return ResponseEntity.ok(menuService.findAllMenus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuResponse>> getMenusByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuService.getMenusByRestaurantId(restaurantId));
    }

    @GetMapping("date/{date}")
    public ResponseEntity<List<MenuResponse>> getMenusByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(menuService.getMenusByDate(date));
    }

    @GetMapping("/restaurant/{restaurantId}/date/{date}")
    public ResponseEntity<List<MenuResponse>> getMenusByRestaurantIdAndDate(@PathVariable Long restaurantId, @PathVariable LocalDate date) {
        return ResponseEntity.ok(menuService.getMenusByRestaurantIdAndDate(restaurantId, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long id, @RequestBody MenuRequest menuRequest) {
        return ResponseEntity.ok(menuService.updateMenu(menuRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }
}
