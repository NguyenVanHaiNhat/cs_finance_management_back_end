package org.example.cs_finance_management_back_end.service;
import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface IExpenseService extends IGeneralService<Expense>{
    Page<Expense> findAllByUser(Pageable pageable, Users users) ;
    Page<Expense> findByTime_now(Pageable pageable,LocalDate time_now, Long user_id);
    double totalAmount(Long user_id);

}
