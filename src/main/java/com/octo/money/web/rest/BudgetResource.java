package com.octo.money.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.octo.money.domain.Budget;
import com.octo.money.repository.BudgetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Budget.
 */
@RestController
@RequestMapping("/app")
public class BudgetResource {

    private final Logger log = LoggerFactory.getLogger(BudgetResource.class);

    @Inject
    private BudgetRepository budgetRepository;

    /**
     * POST  /rest/budgets -> Create a new budget.
     */
    @RequestMapping(value = "/rest/budgets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Budget budget) {
        log.debug("REST request to save Budget : {}", budget);
        budgetRepository.save(budget);
    }

    /**
     * GET  /rest/budgets -> get all the budgets.
     */
    @RequestMapping(value = "/rest/budgets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Budget> getAll() {
        log.debug("REST request to get all Budgets");
        return budgetRepository.findAll();
    }

    /**
     * GET  /rest/budgets/:id -> get the "id" budget.
     */
    @RequestMapping(value = "/rest/budgets/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Budget> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Budget : {}", id);
        Budget budget = budgetRepository.findOne(id);
        if (budget == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/budgets/:id -> delete the "id" budget.
     */
    @RequestMapping(value = "/rest/budgets/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Budget : {}", id);
        budgetRepository.delete(id);
    }
}
