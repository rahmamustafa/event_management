package gov.iti.evento.controllers;

import gov.iti.evento.entites.Category;
import gov.iti.evento.services.CategoryService;
import gov.iti.evento.services.dtos.CategoryCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryCreateDto> getCategories() {
        return categoryService.getCategories();
    }
    @PostMapping("category/{categoryType}")
    public Category addCategory (@PathVariable String categoryType){
        Category category = new Category();
        category.setType(categoryType);
        return categoryService.saveCategory(category);
    }
}
