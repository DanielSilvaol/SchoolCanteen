package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE (:registerDate IS NULL OR CAST(s.registerDate AS date) = :registerDate) ORDER BY s.registerDate DESC")
    List<Sale> findSalesByRegisterDate(@Param("registerDate") LocalDate registerDate);


}
