package com.entando.springbootexpense.service;

import com.entando.springbootexpense.model.entity.ExpenseEntity;
import com.entando.springbootexpense.model.entity.ExpenseStatus;
import com.entando.springbootexpense.model.record.ExpenseRecord;
import com.entando.springbootexpense.repository.ExpenseRepository;
import com.entando.springbootexpense.service.mapper.ExpenseMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public Page<ExpenseRecord> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable)
                .map(expenseMapper::toRecord);
    }

    @Override
    public Optional<ExpenseRecord> getExpense(Long id) {
        return expenseRepository.findOneById(id)
                .map(expenseMapper::toRecord);
    }

    @Override
    public ExpenseRecord save(ExpenseRecord expenseRecord) {
        ExpenseEntity entity = expenseMapper.toEntity(expenseRecord);
        entity.setSubmitted(LocalDateTime.now());
        ExpenseEntity savedEntity = expenseRepository.save(entity);
        return expenseMapper.toRecord(savedEntity);
    }

    @Override
    public ExpenseRecord update(Long id, ExpenseStatus status) {
        if (!exists(id)) {
            throw new IllegalArgumentException("Expense not found with id: " + id);
        }
        expenseRepository.updateStatus(id, status);
        return expenseMapper.toRecord(new ExpenseEntity(id, status));
    }

    @Override
    public boolean exists(Long id) {
        return expenseRepository.existsById(id);
    }

}
