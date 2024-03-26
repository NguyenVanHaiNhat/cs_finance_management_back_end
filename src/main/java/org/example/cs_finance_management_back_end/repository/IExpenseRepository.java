package org.example.cs_finance_management_back_end.repository;

import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findAllByUsers(Pageable pageable, Users users);

@Query(nativeQuery = true,value = "select * from expense where time_now like %?% and user_id = ?")
    Page<Expense> searchByTime_now(Pageable pageable,LocalDate time_now, Long user_id);
    @Query(nativeQuery = true, value = "select sum(amount) as totalAmount from finance_management.expense where user_id = ?")
    double totalAmount(Long user_id);
}
