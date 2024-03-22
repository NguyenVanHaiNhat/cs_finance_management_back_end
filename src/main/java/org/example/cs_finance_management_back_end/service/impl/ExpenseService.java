package org.example.cs_finance_management_back_end.service.impl;

import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.repository.IExpenseRepository;
import org.example.cs_finance_management_back_end.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService implements IExpenseService {
    @Autowired
    private IExpenseRepository iExpenseRepository;

    @Override
    public Page findAll(Pageable pageable) {
        return iExpenseRepository.findAll(pageable);
    }

    @Override
    public Expense save(Expense expense) {
       return iExpenseRepository.save(expense);
    }


    @Override
    public Optional findById(Long id) {
        return iExpenseRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iExpenseRepository.deleteById(id);
    }

    @Override
    public Page<Expense> findAllByUser(Pageable pageable, Users users) {
        return iExpenseRepository.findAllByUsers(pageable,users);
    }
}
