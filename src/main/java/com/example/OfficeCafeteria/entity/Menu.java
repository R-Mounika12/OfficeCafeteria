package com.example.OfficeCafeteria.entity;

import com.example.OfficeCafeteria.constants.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {

    private Long id;
    private Long restaurantId;
    private LocalDate date;
    private MealType mealType;
    private List<Long> menuItemIds;
}
