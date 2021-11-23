package com.gmail.antonovich.tonya.morionkot.controller;


import com.gmail.antonovich.tonya.morionkot.dto.CategoryDto;
import com.gmail.antonovich.tonya.morionkot.exeption.NoSuchEntityException;
import com.gmail.antonovich.tonya.morionkot.service.CategoryService;
import com.gmail.antonovich.tonya.morionkot.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("category")
    public List<CategoryDto> findCategories(){
        return Mapper.mapAll(categoryService.getAllCategories(), CategoryDto.class);
    }

    @PostMapping("new-category")
    public void newCategory(@RequestBody CategoryDto categoryName) throws NoSuchEntityException  {
        categoryService.addCategory(categoryName);
    }

    @PostMapping("delete-category")
    public void deleteCategory(@RequestBody CategoryDto categoryDto) throws NoSuchEntityException {
        categoryService.deleteCategory(categoryDto);
    }
}
