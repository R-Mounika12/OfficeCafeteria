package com.example.OfficeCafeteria.dto;

import com.example.OfficeCafeteria.constants.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MenuItemDto {

    private  String name;

    private String description;

    private Double price;

    private Boolean isVegetarian;

    private ItemCategory category;
}
