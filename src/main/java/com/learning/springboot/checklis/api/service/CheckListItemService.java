package com.learning.springboot.checklis.api.service;

import com.learning.springboot.checklis.api.entity.CategoryEntity;
import com.learning.springboot.checklis.api.entity.CheckListItemEntity;
import com.learning.springboot.checklis.api.exception.ResourceNotFoundException;
import com.learning.springboot.checklis.api.repository.CategoryRepository;
import com.learning.springboot.checklis.api.repository.CheckLIstItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
public class CheckListItemService {

    private CheckLIstItemRepository checkLIstItemRepository;

    private CategoryRepository categoryRepository;

    public CheckListItemService(CheckLIstItemRepository checkLIstItemRepository, CategoryRepository categoryRepository){
        this.checkLIstItemRepository = checkLIstItemRepository;
        this.categoryRepository = categoryRepository;
    }

    private void validateCheckListItemData(String description, Boolean isComplited, LocalDate deadLine, String categoryGuid){
        if(StringUtils.hasText(description)){
            throw new IllegalArgumentException("Checklist item must have a description");
        }

        if(isComplited == null){
            throw new IllegalArgumentException("Checklist Item must have a flag indication if it's completed or not.");
        }

        if(deadLine == null){
            throw new IllegalArgumentException("Checklist Item must have a deadline.");
        }

        if(StringUtils.hasText(categoryGuid)){
            throw new IllegalArgumentException("Checklist Item must have be provided.");
        }
    }

    public CheckListItemEntity addNewCheckListItem(String description, Boolean isComplited, LocalDate deadLine, String categoryGuid){
        this.validateCheckListItemData(description, isComplited, deadLine, categoryGuid);

        CategoryEntity retrivedCategory = this.categoryRepository.findByGuid(categoryGuid).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        CheckListItemEntity checkListItemEntity = new CheckListItemEntity();

        checkListItemEntity.setGuid(UUID.randomUUID().toString());
        checkListItemEntity.setDescription(description);
        checkListItemEntity.setDeadLine(deadLine);
        checkListItemEntity.setPostedDate(LocalDate.now());
        checkListItemEntity.setCategory(retrivedCategory);

        log.debug("Adding a new checklist Item [ checklistItem = {}]", checkListItemEntity);
        return checkLIstItemRepository.save(checkListItemEntity);
    }

    public Iterable<CheckListItemEntity> findAllCheckListItems(){
        return this.checkLIstItemRepository.findAll();
    }

    public void deleteCheckLIstItem(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("G cannot be null or empty");
        }

        CheckListItemEntity retrivedItem = this.checkLIstItemRepository.findByGuid(guid).orElseThrow(()->
                new ResourceNotFoundException("Checklistitem not found"));
        log.debug("Deleting checklist item { guid = {}", guid);

        this.checkLIstItemRepository.delete(retrivedItem);
    }
}
;