package com.octo.money.repository;

import com.octo.money.domain.Budget;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Budget entity.
 */
public interface BudgetRepository extends JpaRepository<Budget, Long> {

}
