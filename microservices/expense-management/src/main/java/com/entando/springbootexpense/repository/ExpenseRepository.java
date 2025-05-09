package com.entando.springbootexpense.repository;

import com.entando.springbootexpense.model.entity.ExpenseEntity;
import com.entando.springbootexpense.model.entity.ExpenseStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, UUID>, PagingAndSortingRepository<ExpenseEntity, UUID> {

    Optional<ExpenseEntity> findOneById(Long id);

    boolean existsById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE ExpenseEntity e SET e.status = :status WHERE e.id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") ExpenseStatus status);


}
