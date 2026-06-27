package com.example.OfficeCafeteria.dto;

import com.example.OfficeCafeteria.constants.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MenuResponse {

    private Long id;

    private String restaurantName;

    private String restaurantLocation;

    private LocalDate date;

    private MealType mealType;

    private List<MenuItemDto> items;
}
