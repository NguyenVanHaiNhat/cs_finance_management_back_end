package org.example.cs_finance_management_back_end.controller;

import org.example.cs_finance_management_back_end.config.service.JwtService;
import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Users;
import org.example.cs_finance_management_back_end.repository.IUsersRepository;
import org.example.cs_finance_management_back_end.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    private IExpenseService iExpenseService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUsersRepository iUsersRepository;
    LocalDate currentDate = LocalDate.now();

    @GetMapping
    public ResponseEntity<Page<Expense>> getAllExpense(Pageable pageable, @RequestHeader("Authorization") String tokenHeader) {
       String token = tokenHeader.substring(7);
       String users = jwtService.getUsernameFromJwtToken(token);
       Users user = iUsersRepository.findByUsername(users);
        Page<Expense> expenses = iExpenseService.findAllByUser(pageable,user);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        String users = jwtService.getUsernameFromJwtToken(token);
        expense.setUsers(iUsersRepository.findByUsername(users));
        expense.setTime_now(currentDate);
        Expense expense1 = iExpenseService.save(expense);
        return new ResponseEntity<>(expense1, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id ) {
        Optional<Expense> expenseOptional = iExpenseService.findById(id);
        if (!expenseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(expenseOptional.get(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Expense> updateExpense( @RequestBody Expense expense, @RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.substring(7);
        String users = jwtService.getUsernameFromJwtToken(token);
        expense.setTime_now(currentDate);
        expense.setUsers(iUsersRepository.findByUsername(users));
       Expense updateExpense = iExpenseService.save(expense);

        return new ResponseEntity<>(updateExpense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Optional<Expense> expenseOptional = iExpenseService.findById(id);
        if (!expenseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iExpenseService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
