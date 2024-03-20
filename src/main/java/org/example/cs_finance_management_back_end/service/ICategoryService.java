package org.example.cs_finance_management_back_end.service;

import org.example.cs_finance_management_back_end.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(Long id);
    Category save(Category category);
    void remove(Long id);
}
