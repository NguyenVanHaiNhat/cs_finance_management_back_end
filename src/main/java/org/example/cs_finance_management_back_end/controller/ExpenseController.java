package org.example.cs_finance_management_back_end.controller;

import org.example.cs_finance_management_back_end.model.entity.Category;
import org.example.cs_finance_management_back_end.model.entity.Expense;
import org.example.cs_finance_management_back_end.model.entity.Walletdetails;
import org.example.cs_finance_management_back_end.service.ICategoryService;
import org.example.cs_finance_management_back_end.service.IExpenseService;
import org.example.cs_finance_management_back_end.service.IWalletdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    private IExpenseService iExpenseService;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IWalletdetailService iWalletdetailService;


    @ModelAttribute("id_category")
    public Page<Category> findAllCategory(Pageable pageable) {

        return iCategoryService.findAll(pageable);
    }



    @ModelAttribute("id_walletDetails")
    public Page<Walletdetails> findAllWalletdetails(Pageable pageable) {
        return iWalletdetailService.findAll(pageable);
    }

    @GetMapping
    public ResponseEntity<Page<Expense>> getAllExpense(Pageable pageable) {
        Page<Expense> expenses = iExpenseService.findAll(pageable);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense expense1 = iExpenseService.save(expense);
        return new ResponseEntity<>(expense1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expenseOptional = iExpenseService.findById(id);
        if (!expenseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenseOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Optional<Expense> expenseOptional = iExpenseService.findById(id);
        expense.setId(expenseOptional.get().getId());
        return new ResponseEntity<>(iExpenseService.save(expense), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long id) {
        Optional<Expense> expenseOptional = iExpenseService.findById(id);
        if (expenseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iExpenseService.remove(id);
        return new ResponseEntity<>(expenseOptional.get(), HttpStatus.OK);
    }
}
