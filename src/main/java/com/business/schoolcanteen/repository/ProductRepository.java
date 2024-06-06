package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameLikeIgnoreCaseAndActiveTrue(String name);
}
