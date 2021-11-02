package com.learning.springboot.checklis.api.entity;

import lombok.Data;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity {

    private String guid;
}
