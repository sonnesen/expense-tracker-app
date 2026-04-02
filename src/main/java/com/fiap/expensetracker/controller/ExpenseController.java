package com.fiap.expensetracker.controller;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.expensetracker.entity.Expense;
import com.fiap.expensetracker.service.ExpenseService;
import com.fiap.expensetrackerapp.api.ExpensesApi;
import com.fiap.expensetrackerapp.api.model.ExpenseRequest;
import com.fiap.expensetrackerapp.api.model.ExpenseResponse;

import jakarta.validation.Valid;

@RestController
public class ExpenseController implements ExpensesApi {

    private final ExpenseService expenseService;

    public ExpenseController(final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    public ResponseEntity<Void> createExpense(@Valid ExpenseRequest expenseRequest) {
        final var expense = new Expense();
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(Double.valueOf(expenseRequest.getAmount()));
        expense.setCategory(expenseRequest.getCategory());

        expenseService.createExpense(expense);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<ExpenseResponse>> getExpenses(@Nullable @Valid String period) {
        final var expenses = expenseService.list(period);

        final var response = expenses.stream().map(expense -> {
            final var expenseResponse = new ExpenseResponse();
            expenseResponse.setId(expense.getId().toString());
            expenseResponse.setDescription(expense.getDescription());
            expenseResponse.setAmount(expense.getAmount().floatValue());
            expenseResponse.setDate(OffsetDateTime.of(expense.getCreatedAt(), ZoneOffset.UTC));
            expenseResponse.setCategory(expense.getCategory());
            return expenseResponse;
        }).toList();

        return ResponseEntity.ok(response);
    }

}
