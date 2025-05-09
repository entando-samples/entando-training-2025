package com.entando.springbootexpense.model.record;

import com.entando.springbootexpense.model.entity.ExpenseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExpenseRecord(long id, String title, BigDecimal expense, @JsonProperty("createdAt")
 LocalDateTime submitted, LocalDateTime date, ExpenseStatus status) { }
