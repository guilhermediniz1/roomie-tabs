package com.guilhermediniz.roomie_tabs.entity;

import com.guilhermediniz.roomie_tabs.entity.constant.ExpenseType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal value;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;
}
