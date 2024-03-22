package org.example.cs_finance_management_back_end.repository;

import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpenseRepository extends JpaRepository<Expense,Long> {
    Page<Expense> findAllByUsers(Pageable pageable, Users users);
}
