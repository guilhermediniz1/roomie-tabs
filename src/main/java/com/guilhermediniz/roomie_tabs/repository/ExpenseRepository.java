package com.guilhermediniz.roomie_tabs.repository;


import com.guilhermediniz.roomie_tabs.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
