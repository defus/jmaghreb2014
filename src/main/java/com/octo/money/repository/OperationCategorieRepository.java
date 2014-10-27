package com.octo.money.repository;

import com.octo.money.domain.OperationCategorie;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the OperationCategorie entity.
 */
public interface OperationCategorieRepository extends JpaRepository<OperationCategorie, Long> {

}
