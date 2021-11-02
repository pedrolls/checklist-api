package com.learning.springboot.checklis.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalTime;

@Data
@Entity(name = "CheckListItem")
@Table(indexes = {@Index(name = "IDX_GUID.CK_IT", columnList = "guid")})
public class CheckListItemEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkListItemId;

    private String description;

    private Boolean isCompleted;

    private LocalTime deadLine;

    private LocalTime postedDate;

    @ManyToOne
    private CategoryEntity category;
}
