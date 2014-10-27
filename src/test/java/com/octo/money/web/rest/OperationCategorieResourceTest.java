package com.octo.money.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

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
import com.octo.money.domain.OperationCategorie;
import com.octo.money.repository.OperationCategorieRepository;

/**
 * Test class for the OperationCategorieResource REST controller.
 *
 * @see OperationCategorieResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
public class OperationCategorieResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);
    
    private static final String DEFAULT_LIBELLE = "SAMPLE_TEXT";
    private static final String UPDATED_LIBELLE = "UPDATED_TEXT";
        
    @Inject
    private OperationCategorieRepository operationcategorieRepository;

    private MockMvc restOperationCategorieMockMvc;

    private OperationCategorie operationcategorie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OperationCategorieResource operationcategorieResource = new OperationCategorieResource();
        ReflectionTestUtils.setField(operationcategorieResource, "operationcategorieRepository", operationcategorieRepository);

        this.restOperationCategorieMockMvc = MockMvcBuilders.standaloneSetup(operationcategorieResource).build();

        operationcategorie = new OperationCategorie();
        operationcategorie.setId(DEFAULT_ID);

        operationcategorie.setLibelle(DEFAULT_LIBELLE);
    }

    @Test
    public void testCRUDOperationCategorie() throws Exception {

        // Create OperationCategorie
        restOperationCategorieMockMvc.perform(post("/app/rest/operationcategories")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(operationcategorie)))
                .andExpect(status().isOk());

        // Read OperationCategorie
        restOperationCategorieMockMvc.perform(get("/app/rest/operationcategories/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));

        // Update OperationCategorie
        operationcategorie.setLibelle(UPDATED_LIBELLE);

        restOperationCategorieMockMvc.perform(post("/app/rest/operationcategories")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(operationcategorie)))
                .andExpect(status().isOk());

        // Read updated OperationCategorie
        restOperationCategorieMockMvc.perform(get("/app/rest/operationcategories/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.libelle").value(UPDATED_LIBELLE.toString()));

        // Delete OperationCategorie
        restOperationCategorieMockMvc.perform(delete("/app/rest/operationcategories/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Read nonexisting OperationCategorie
        restOperationCategorieMockMvc.perform(get("/app/rest/operationcategories/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
