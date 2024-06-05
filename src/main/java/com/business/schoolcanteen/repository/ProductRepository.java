package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE CONCAT('%', UPPER(?1) , '%') ")
    List<Product> findByNameLikeIgnoreCaseAndActiveTrue(String name);
}
