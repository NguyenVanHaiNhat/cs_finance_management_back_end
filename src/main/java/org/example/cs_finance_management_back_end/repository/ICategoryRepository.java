package org.example.cs_finance_management_back_end.repository;

import org.example.cs_finance_management_back_end.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
