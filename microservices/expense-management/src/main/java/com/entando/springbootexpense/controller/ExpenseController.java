package com.entando.springbootexpense.controller;


import com.entando.springbootexpense.model.entity.ExpenseStatus;
import com.entando.springbootexpense.model.record.ExpenseRecord;
import com.entando.springbootexpense.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final Logger log = LoggerFactory.getLogger(ExpenseController.class);
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseRecord>> getAllExpenses(@ParameterObject Pageable pageable) {
        log.debug("REST request to get all Expenses");

        final Page<ExpenseRecord> page = expenseService.getAllExpenses(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
                ServletUriComponentsBuilder.fromCurrentRequest(), page);

        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpenseRecord> getExpense(@PathVariable Long id) {
        log.debug("REST request to get the expense with id {}", id);

        return expenseService
                .getExpense(id)
                .map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/expenses")
    @PreAuthorize("hasAuthority('expense-admin')")
    @SecurityRequirement(name = "expense-admin")
    public ResponseEntity<ExpenseRecord> createExpense(@RequestBody ExpenseRecord expense) throws URISyntaxException {
        log.debug("REST request to create a NEW expense: {}", expense);

        ExpenseRecord created = expenseService.save(expense);

        return ResponseEntity
                .created(new URI("/api/expenses/" + created.id()))
                .body(created);
    }

    @PatchMapping("/expenses/{id}/{status}")
    @PreAuthorize("hasAuthority('expense-admin')")
    @SecurityRequirement(name = "expense-admin")
    public ResponseEntity<ExpenseRecord> updateStatus(@PathVariable Long id, @PathVariable ExpenseStatus status) {
        log.debug("REST request to update expense: {} status to {}", id, status);

        return expenseService.exists(id)
                ? ResponseEntity.ok(expenseService.update(id, status))
                : ResponseEntity.notFound().build();
    }

}
