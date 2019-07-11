package com.copeika.monex.api;

import com.copeika.monex.models.Budget;
import com.copeika.monex.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BudgetController {
    private static final String BUDGET_PATH = "/api/v001/budget";

    @Autowired
    private BudgetService service;

    @PostMapping(BUDGET_PATH)
    public ResponseEntity<Budget> createBudget(
            @RequestBody Integer money
    ) {
        Budget result = service.createBudget(money);
        return ResponseEntity.ok(result);
    }

    @GetMapping(BUDGET_PATH)
    public ResponseEntity<Budget> takeBudget() {
        Budget result = service.provideBudget();
        return ResponseEntity.ok(result);
    }

    @PatchMapping(BUDGET_PATH)
    public ResponseEntity<?> updateBudget(
            @RequestBody Integer money
    ) {
        Budget update = service.updateBudget(money);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping(BUDGET_PATH)
    public ResponseEntity<?> deleteBudget(
    ) {
        service.deleteBudget();
        return ResponseEntity.ok().build();
    }

}
