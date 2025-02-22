package com.guilhermediniz.roomie_tabs.controller;

import com.guilhermediniz.roomie_tabs.entity.Expense;
import com.guilhermediniz.roomie_tabs.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> list() {
        return expenseService.getExpenses();
    }

    @PostMapping
    public Expense create(@RequestBody Expense despesa) {
        return expenseService.save(despesa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        expenseService.delete(id);
    }
}
