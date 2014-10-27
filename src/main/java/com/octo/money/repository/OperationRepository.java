package com.octo.money.repository;

import com.octo.money.domain.Operation;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Operation entity.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
