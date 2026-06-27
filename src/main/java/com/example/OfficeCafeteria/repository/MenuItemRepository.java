package com.example.OfficeCafeteria.repository;

import com.example.OfficeCafeteria.entity.MenuItem;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Repository
public class MenuItemRepository {

    private Map<Long, MenuItem> menuItemMap = new HashMap<>();
    private static AtomicLong idCounter = new AtomicLong(1L);

    public MenuItem save(MenuItem menuItem) {
        if(menuItem.getId() == null) {
            menuItem.setId(idCounter.getAndIncrement());
        }
        menuItemMap.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public MenuItem findById(Long id) {
        if(id != null && id > 0 && menuItemMap.containsKey(id)){
            return menuItemMap.get(id);
        }else {
            return null;
        }
    }

    public List<MenuItem> findAll() {
        return menuItemMap.values().stream().toList();
    }

    public MenuItem findByName(String name) {
        return menuItemMap.values().stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst().orElse(null);
    }

    public MenuItem update(Long id, MenuItem menuItem) {
        if(id != null && id > 0 && menuItemMap.containsKey(id)) {
            menuItemMap.put(id, menuItem);
            return menuItem;
        }else {
            return null;
        }
    }

    public void delete(Long id) {
        if(menuItemMap.containsKey(id)) {
            menuItemMap.remove(id);
        }
    }

    public List<MenuItem> findMenuItemsByIds(List<Long> ids) {
        // Optimized: Direct map lookup O(m) instead of filtering all items O(n*m)
        return ids.stream()
                .map(menuItemMap::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
