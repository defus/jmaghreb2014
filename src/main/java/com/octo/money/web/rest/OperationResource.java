package com.octo.money.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.octo.money.domain.Operation;
import com.octo.money.repository.OperationRepository;

/**
 * REST controller for managing Operation.
 */
@RestController
@RequestMapping("/app")
public class OperationResource {

    private final Logger log = LoggerFactory.getLogger(OperationResource.class);

    @Inject
    private OperationRepository operationRepository;

    /**
     * POST  /rest/operations -> Create a new operation.
     */
    @RequestMapping(value = "/rest/operations",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Operation operation) {
        log.debug("REST request to save Operation : {}", operation);
        operationRepository.save(operation);
    }

    /**
     * GET  /rest/operations -> get all the operations.
     */
    @RequestMapping(value = "/rest/operations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Operation> getAll(@RequestParam(defaultValue="0") int page,
    		@RequestParam(defaultValue="20") int size, 
    		@RequestParam(defaultValue="dateOperation") String sortColumn,
    		@RequestParam(defaultValue="DESC") String sortOrder) {
    	Pageable pageable = new PageRequest(
    		      page, size, new Sort(Sort.Direction.fromString(sortOrder), sortColumn)
    		    );
        log.debug("REST request to get all Operations : page={}, size={}", page, size);
        return operationRepository.findAll(pageable).getContent();
    }

    /**
     * GET  /rest/operations/:id -> get the "id" operation.
     */
    @RequestMapping(value = "/rest/operations/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Operation> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Operation : {}", id);
        Operation operation = operationRepository.findOne(id);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/operations/:id -> delete the "id" operation.
     */
    @RequestMapping(value = "/rest/operations/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Operation : {}", id);
        operationRepository.delete(id);
    }
}
