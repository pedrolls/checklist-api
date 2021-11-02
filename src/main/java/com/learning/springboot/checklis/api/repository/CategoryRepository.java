package com.learning.springboot.checklis.api.repository;

import com.learning.springboot.checklis.api.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByGuid(String guid);
}
