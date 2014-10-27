package com.octo.money.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;
import org.joda.time.LocalDate;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.octo.money.Application;
import com.octo.money.domain.Budget;
import com.octo.money.repository.BudgetRepository;

/**
 * Test class for the BudgetResource REST controller.
 *
 * @see BudgetResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
public class BudgetResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);
    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
        
    private static final LocalDate DEFAULT_DATE_DEBUT = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = new LocalDate();
        
    private static final LocalDate DEFAULT_DATE_FIN = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_FIN = new LocalDate();
        
    private static final BigDecimal DEFAULT_MONTANT = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_MONTANT = BigDecimal.ONE;
        
    @Inject
    private BudgetRepository budgetRepository;

    private MockMvc restBudgetMockMvc;

    private Budget budget;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BudgetResource budgetResource = new BudgetResource();
        ReflectionTestUtils.setField(budgetResource, "budgetRepository", budgetRepository);

        this.restBudgetMockMvc = MockMvcBuilders.standaloneSetup(budgetResource).build();

        budget = new Budget();
        budget.setId(DEFAULT_ID);

        budget.setDescription(DEFAULT_DESCRIPTION);
        budget.setDateDebut(DEFAULT_DATE_DEBUT);
        budget.setDateFin(DEFAULT_DATE_FIN);
        budget.setMontant(DEFAULT_MONTANT);
    }

    @Test
    public void testCRUDBudget() throws Exception {

        // Create Budget
        restBudgetMockMvc.perform(post("/app/rest/budgets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(budget)))
                .andExpect(status().isOk());

        // Read Budget
        restBudgetMockMvc.perform(get("/app/rest/budgets/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
                .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
                .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()));

        // Update Budget
        budget.setDescription(UPDATED_DESCRIPTION);
        budget.setDateDebut(UPDATED_DATE_DEBUT);
        budget.setDateFin(UPDATED_DATE_FIN);
        budget.setMontant(UPDATED_MONTANT);

        restBudgetMockMvc.perform(post("/app/rest/budgets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(budget)))
                .andExpect(status().isOk());

        // Read updated Budget
        restBudgetMockMvc.perform(get("/app/rest/budgets/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.description").value(UPDATED_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.dateDebut").value(UPDATED_DATE_DEBUT.toString()))
                .andExpect(jsonPath("$.dateFin").value(UPDATED_DATE_FIN.toString()))
                .andExpect(jsonPath("$.montant").value(UPDATED_MONTANT.doubleValue()));

        // Delete Budget
        restBudgetMockMvc.perform(delete("/app/rest/budgets/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting Budget
        restBudgetMockMvc.perform(get("/app/rest/budgets/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
