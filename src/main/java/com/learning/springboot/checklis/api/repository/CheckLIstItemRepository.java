package com.learning.springboot.checklis.api.repository;

import com.learning.springboot.checklis.api.entity.CategoryEntity;
import com.learning.springboot.checklis.api.entity.CheckListItemEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CheckLIstItemRepository extends PagingAndSortingRepository<CheckListItemEntity, Long> {

    Optional<CheckListItemEntity> findByGuid(String guid);

    List<CheckListItemEntity> FindByDescriptionAndIsCompleted(String description, Boolean isCompleted);
}
