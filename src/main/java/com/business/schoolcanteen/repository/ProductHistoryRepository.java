package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.ProductHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductHistoryRepository extends MongoRepository<ProductHistory, String> {
}
