package com.fiap.expensetracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.fiap.expensetracker.entity.Expense;
import com.fiap.expensetracker.repository.ExpenseRepository;

import jakarta.transaction.Transactional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(final ExpenseRepository expenseRepository) {
        this.expenseRepository = Objects.requireNonNull(expenseRepository, "expenseRepository must not be null");
    }

    @Transactional
    public Expense createExpense(final Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> list(final String period) {
        final var now = LocalDateTime.now();

        if ("WEEK".equalsIgnoreCase(period)) {
            return expenseRepository.findByCreatedAtAfter(now.minusWeeks(1));
        }

        if ("MONTH".equalsIgnoreCase(period)) {
            return expenseRepository.findByCreatedAtAfter(now.minusMonths(1));
        }

        return expenseRepository.findAll();
    }
}
