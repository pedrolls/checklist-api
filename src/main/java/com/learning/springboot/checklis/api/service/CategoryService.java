package com.learning.springboot.checklis.api.service;

import com.learning.springboot.checklis.api.entity.CategoryEntity;
import com.learning.springboot.checklis.api.entity.CheckListItemEntity;
import com.learning.springboot.checklis.api.exception.CategoryServiceException;
import com.learning.springboot.checklis.api.exception.ResourceNotFoundException;
import com.learning.springboot.checklis.api.repository.CategoryRepository;
import com.learning.springboot.checklis.api.repository.CheckLIstItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CategoryService {

    private CheckLIstItemRepository checkListItemRepository;
    private CategoryRepository categoryRepository;

    //Construtor que faz o papel do autowired
    public CategoryService(CheckLIstItemRepository checkListItemRepository, CategoryRepository categoryRepository){
        this.checkListItemRepository = checkListItemRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity addNewCategory(String name){
        if(!StringUtils.hasText(name)){
            throw new IllegalArgumentException("Category name cannot be empty or null");
        }

        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setGuid(UUID.randomUUID().toString());
        newCategory.setName(name);

        log.debug("Adding a new category with name [ name = {} ]", name);
        return this.categoryRepository.save(newCategory);
    }

    public CategoryEntity updateCategory(String guid, String name){
        if(guid == null || StringUtils.hasText(name)){
            throw new IllegalArgumentException("Invalid Paramteres provided to update a category");
        }

        CategoryEntity retrivedCategory = this.categoryRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Category not found.")
        );

        retrivedCategory.setName(name);
        log.debug("Updating category [guid = {}, newName = {} ]", guid, name);

        return this.categoryRepository.save(retrivedCategory);
    }

    public void deleteCategory(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("Category guid cannot be empty or null");
        }

        CategoryEntity retrivedCategory = this.categoryRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Category not found.")
        );

        List<CheckListItemEntity> checkListItemList = this.checkListItemRepository.findByCategoryGuid(guid);

        if(!CollectionUtils.isEmpty(checkListItemList)){
            throw new CategoryServiceException("It is not possible to delete given category as ti has been used by itens");
        }
        log.debug("deleting category [ guid = {} ]", guid);
        this.categoryRepository.delete(retrivedCategory);
    }

    public Iterable<CategoryEntity> findAllCategories(){
        return this.categoryRepository.findAll();
    }

    public CategoryEntity findCategoryByGuid(String guid){
        if(!StringUtils.hasText(guid)){
            throw new IllegalArgumentException("Category guid cannot be empty or null");
        }

        return this.categoryRepository.findByGuid(guid).orElseThrow(
                () -> new ResourceNotFoundException("Category not found."));
    }
}
