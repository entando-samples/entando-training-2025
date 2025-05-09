package com.entando.springbootexpense.service.mapper;

import com.entando.springbootexpense.model.entity.ExpenseEntity;
import com.entando.springbootexpense.model.record.ExpenseRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseEntity toEntity(ExpenseRecord recordIn) {
        ExpenseEntity entityOut = new ExpenseEntity();
        BeanUtils.copyProperties(recordIn, entityOut);
        return entityOut;
    }

    public ExpenseRecord toRecord(ExpenseEntity entity) {
        return new ExpenseRecord(entity.getId(),
                entity.getTitle(),
                entity.getExpense(),
                entity.getSubmitted(),
                entity.getDate(),
                entity.getStatus());
    }


}
