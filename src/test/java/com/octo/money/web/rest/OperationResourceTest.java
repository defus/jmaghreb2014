package com.octo.money.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
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
import com.octo.money.domain.Operation;
import com.octo.money.repository.OperationRepository;

/**
 * Test class for the OperationResource REST controller.
 *
 * @see OperationResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
public class OperationResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);
    
    private static final String DEFAULT_DESCRIPTION = "SAMPLE_TEXT";
    private static final String UPDATED_DESCRIPTION = "UPDATED_TEXT";
        
    private static final LocalDate DEFAULT_DATE_OPERATION = new LocalDate(0L);
    private static final LocalDate UPDATED_DATE_OPERATION = new LocalDate();
        
    private static final BigDecimal DEFAULT_MONTANT = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_MONTANT = BigDecimal.ONE;
        
        
    @Inject
    private OperationRepository operationRepository;

    private MockMvc restOperationMockMvc;

    private Operation operation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OperationResource operationResource = new OperationResource();
        ReflectionTestUtils.setField(operationResource, "operationRepository", operationRepository);

        this.restOperationMockMvc = MockMvcBuilders.standaloneSetup(operationResource).build();

        operation = new Operation();
        operation.setId(DEFAULT_ID);

        operation.setDescription(DEFAULT_DESCRIPTION);
        operation.setDateOperation(DEFAULT_DATE_OPERATION);
        operation.setMontant(DEFAULT_MONTANT);
        
    }

    @Test
    public void testCRUDOperation() throws Exception {

        // Create Operation
        restOperationMockMvc.perform(post("/app/rest/operations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(operation)))
                .andExpect(status().isOk());

        // Read Operation
        restOperationMockMvc.perform(get("/app/rest/operations/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.dateOperation").value(DEFAULT_DATE_OPERATION.toString()))
                .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()));

        // Update Operation
        operation.setDescription(UPDATED_DESCRIPTION);
        operation.setDateOperation(UPDATED_DATE_OPERATION);
        operation.setMontant(UPDATED_MONTANT);

        restOperationMockMvc.perform(post("/app/rest/operations")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(operation)))
                .andExpect(status().isOk());

        // Read updated Operation
        restOperationMockMvc.perform(get("/app/rest/operations/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.description").value(UPDATED_DESCRIPTION.toString()))
                .andExpect(jsonPath("$.dateOperation").value(UPDATED_DATE_OPERATION.toString()))
                .andExpect(jsonPath("$.montant").value(UPDATED_MONTANT.doubleValue()));

        // Delete Operation
        restOperationMockMvc.perform(delete("/app/rest/operations/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting Operation
        restOperationMockMvc.perform(get("/app/rest/operations/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
