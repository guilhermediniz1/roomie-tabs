package com.guilhermediniz.roomie_tabs.service;

import com.guilhermediniz.roomie_tabs.entity.Expense;
import com.guilhermediniz.roomie_tabs.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;


    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }
    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }
    public void delete(Long id) {
        expenseRepository.deleteById(id);
    }
}
