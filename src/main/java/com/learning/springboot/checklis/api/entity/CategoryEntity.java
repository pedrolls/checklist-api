package com.learning.springboot.checklis.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class CategoryEntity extends BaseEntity{

    private Long categoryId;

    private String name;

    private List<CheckListItemEntity> checkListItems;
}
