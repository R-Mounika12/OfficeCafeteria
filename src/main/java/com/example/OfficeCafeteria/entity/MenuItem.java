package com.example.OfficeCafeteria.entity;

import com.example.OfficeCafeteria.constants.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Boolean isVegetarian;

    private ItemCategory category;
}
