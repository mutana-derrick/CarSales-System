package com.mutana.CarSales.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    public CategoryModel saveCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
