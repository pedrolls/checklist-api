package com.learning.springboot.checklis.api.repository;

import com.learning.springboot.checklis.api.entity.CheckListItemEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckLIstItemRepository extends PagingAndSortingRepository<CheckListItemEntity, Long> {

    Optional<CheckListItemEntity> findByGuid(String guid);

    List<CheckListItemEntity> findByCategoryGuid(String guid);
}
