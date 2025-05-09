package com.entando.springbootexpense.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;

    @Column(precision = 19, scale = 2)
    private BigDecimal expense;
    private LocalDateTime submitted;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExpenseStatus status;


    public ExpenseEntity() { }

    public ExpenseEntity(long id, String title, BigDecimal expense, LocalDateTime submitted, LocalDateTime date, ExpenseStatus status) {
        this.id = id;
        this.title = title;
        this.expense = expense;
        this.submitted = submitted;
        this.date = date;
        this.status = status;
    }

    public ExpenseEntity(Long id, ExpenseStatus status) {
        this.setId(id);
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public LocalDateTime getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDateTime submitted) {
        this.submitted = submitted;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ExpenseStatus getStatus() {
        return status;
    }

    public void setStatus(ExpenseStatus status) {
        this.status = status;
    }
}
