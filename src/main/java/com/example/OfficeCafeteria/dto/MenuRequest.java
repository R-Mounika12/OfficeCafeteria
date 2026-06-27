package com.example.OfficeCafeteria.dto;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MenuRequest {

    private Long id;

    private Long restaurantId;

    private LocalDate date;

    private MealType mealType;

    private List<Long> menuItemIds;
}
