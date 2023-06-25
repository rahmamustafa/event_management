package gov.iti.evento.services;

import gov.iti.evento.entites.Category;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.services.dtos.CategoryDto;
import gov.iti.evento.services.mappers.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories() {
        List<Category> categories = Collections.unmodifiableList(categoryRepository.findAll());
        return categories.stream().map(categoryMapper.INSTANCE::toDto).toList();
    }
}
