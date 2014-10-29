package com.octo.money.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.money.domain.Operation;
import com.octo.money.domain.OperationCategorie;
import com.octo.money.domain.PieValue;
import com.octo.money.domain.SerieValue;

/**
 * Spring Data JPA repository for the Operation entity.
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select new com.octo.money.domain.SerieValue(MONTH(o.dateOperation), sum(o.montant)) from Operation o where o.operationCategorie = :cat and o.dateOperation >= :dateDebut group by MONTH(o.dateOperation) order by MONTH(o.dateOperation) ASC")
	List<SerieValue> getStatsYearByCategorie(@Param("cat") OperationCategorie categorie, @Param("dateDebut") LocalDate dateDebut);
	
	@Query("select new com.octo.money.domain.SerieValue(MONTH(o.dateOperation), sum(o.montant)) from Operation o where o.dateOperation >= :dateDebut group by MONTH(o.dateOperation) order by MONTH(o.dateOperation) ASC")
	List<SerieValue> getStatsYear(@Param("dateDebut") LocalDate dateDebut);
	
	@Query("select new com.octo.money.domain.PieValue(o.operationCategorie.libelle, sum(o.montant)) from Operation o where o.dateOperation >= :dateDebut AND o.dateOperation < :dateFin group by o.operationCategorie.libelle")
	List<PieValue> getStatsMonth(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

}
