package com.gmail.antonovich.tonya.morionkot.service;


import com.gmail.antonovich.tonya.morionkot.exeption.NoSuchEntityException;
import com.gmail.antonovich.tonya.morionkot.repository.CategoryRepository;
import com.gmail.antonovich.tonya.morionkot.dto.CategoryDto;
import com.gmail.antonovich.tonya.morionkot.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(CategoryDto categoryName) throws NoSuchEntityException {
        Category category = new Category();
        category.setName(categoryName.name);
        categoryRepository.save(category);
    }

    public void deleteCategory(CategoryDto categoryDto) throws NoSuchEntityException {
        categoryRepository.deleteById(categoryDto.id);
    }
}
