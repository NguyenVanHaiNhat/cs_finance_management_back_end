package org.example.cs_finance_management_back_end.repository;


import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpenseRepository extends JpaRepository<Expense,Long> {
}
