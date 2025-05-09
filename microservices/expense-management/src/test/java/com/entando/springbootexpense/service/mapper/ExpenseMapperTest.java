package com.entando.springbootexpense.service.mapper;

import com.entando.springbootexpense.model.entity.ExpenseEntity;
import com.entando.springbootexpense.model.entity.ExpenseStatus;
import com.entando.springbootexpense.model.record.ExpenseRecord;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ExpenseMapperTest {

    private ExpenseMapper expenseMapper;
    private ExpenseEntity testEntity;
    private ExpenseRecord testRecord;

    @BeforeEach
    void setUp() {
        expenseMapper = new ExpenseMapper();

        testEntity = new ExpenseEntity();
        testEntity.setId(1L);
        testEntity.setTitle("Pranzo di lavoro");
        testEntity.setExpense(BigDecimal.valueOf(50.0));
        testEntity.setSubmitted(LocalDateTime.now());
        testEntity.setDate(LocalDateTime.now());
        testEntity.setStatus(ExpenseStatus.APPROVED);

        testRecord = new ExpenseRecord(
                1L,
                "Pranzo di lavoro",
                BigDecimal.valueOf(50.0),
                LocalDateTime.now(),
                LocalDateTime.now(),
                ExpenseStatus.APPROVED
        );
    }

    @Test
    void toEntity_ShouldMapAllFields() {
        // When
        ExpenseEntity result = expenseMapper.toEntity(testRecord);

        // Then
        assertNotNull(result);
        assertEquals(testRecord.id(), result.getId());
        assertEquals(testRecord.title(), result.getTitle());
        assertEquals(testRecord.expense(), result.getExpense());
        assertEquals(testRecord.submitted(), result.getSubmitted());
        assertEquals(testRecord.date(), result.getDate());
        assertEquals(testRecord.status(), result.getStatus());
    }

    @Test
    void toRecord_ShouldMapAllFields() {
        // When
        ExpenseRecord result = expenseMapper.toRecord(testEntity);

        // Then
        assertNotNull(result);
        assertEquals(testEntity.getId(), result.id());
        assertEquals(testEntity.getTitle(), result.title());
        assertEquals(testEntity.getExpense(), result.expense());
        assertEquals(testEntity.getSubmitted(), result.submitted());
        assertEquals(testEntity.getDate(), result.date());
        assertEquals(testEntity.getStatus(), result.status());
    }


}
