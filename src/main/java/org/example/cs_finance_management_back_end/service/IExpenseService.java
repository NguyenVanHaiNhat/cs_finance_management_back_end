package org.example.cs_finance_management_back_end.service;
import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.model.entity.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExpenseService extends IGeneralService<Expense>{
    Page<Expense> findAllByUser(Pageable pageable, Users users) ;
}
