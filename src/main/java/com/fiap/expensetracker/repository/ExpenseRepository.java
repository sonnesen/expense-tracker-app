package com.fiap.expensetracker.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.expensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    List<Expense> findByCreatedAtAfter(LocalDateTime minusWeeks);

}
