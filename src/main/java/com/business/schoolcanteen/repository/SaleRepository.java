package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.Sale;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleRepository extends MongoRepository<Sale, String> {
}

