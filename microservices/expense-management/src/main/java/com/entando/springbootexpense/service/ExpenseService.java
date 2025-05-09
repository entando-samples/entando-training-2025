package com.entando.springbootexpense.service;

import com.entando.springbootexpense.model.entity.ExpenseStatus;
import com.entando.springbootexpense.model.record.ExpenseRecord;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    Page<ExpenseRecord> getAllExpenses(Pageable pageable);

    Optional<ExpenseRecord> getExpense(Long id);

    ExpenseRecord save(ExpenseRecord contact);

    ExpenseRecord update(Long id, ExpenseStatus status);

    boolean exists(Long id);
}
