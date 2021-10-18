package com.learning.springboot.checklis.api.entity;

import java.time.LocalTime;

public class CheckListItemEntity extends BaseEntity{

    private Long checkListItemId;

    private String description;

    private Boolean isCompleted;

    private LocalTime deadLine;

    private LocalTime postedDate;

    private CategoryEntity category;
}
