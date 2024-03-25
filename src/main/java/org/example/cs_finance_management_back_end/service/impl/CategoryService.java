package org.example.cs_finance_management_back_end.service.impl;

import org.example.cs_finance_management_back_end.model.entity.Category;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.repository.ICategoryRepository;
import org.example.cs_finance_management_back_end.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> findAllByUsers(Pageable pageable, Users users) {
        return categoryRepository.findAllByUsers(pageable,users);
    }

    @Override
    public Iterable<Category> findAllByUsers(Users users) {
        return categoryRepository.findAllByUsers(users);
    }
}
