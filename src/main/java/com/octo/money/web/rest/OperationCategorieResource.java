package com.octo.money.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.octo.money.domain.OperationCategorie;
import com.octo.money.repository.OperationCategorieRepository;
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
 * REST controller for managing OperationCategorie.
 */
@RestController
@RequestMapping("/app")
public class OperationCategorieResource {

    private final Logger log = LoggerFactory.getLogger(OperationCategorieResource.class);

    @Inject
    private OperationCategorieRepository operationcategorieRepository;

    /**
     * POST  /rest/operationcategories -> Create a new operationcategorie.
     */
    @RequestMapping(value = "/rest/operationcategories",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody OperationCategorie operationcategorie) {
        log.debug("REST request to save OperationCategorie : {}", operationcategorie);
        operationcategorieRepository.save(operationcategorie);
    }

    /**
     * GET  /rest/operationcategories -> get all the operationcategories.
     */
    @RequestMapping(value = "/rest/operationcategories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<OperationCategorie> getAll() {
        log.debug("REST request to get all OperationCategories");
        return operationcategorieRepository.findAll();
    }

    /**
     * GET  /rest/operationcategories/:id -> get the "id" operationcategorie.
     */
    @RequestMapping(value = "/rest/operationcategories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OperationCategorie> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get OperationCategorie : {}", id);
        OperationCategorie operationcategorie = operationcategorieRepository.findOne(id);
        if (operationcategorie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operationcategorie, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/operationcategories/:id -> delete the "id" operationcategorie.
     */
    @RequestMapping(value = "/rest/operationcategories/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete OperationCategorie : {}", id);
        operationcategorieRepository.delete(id);
    }
}
