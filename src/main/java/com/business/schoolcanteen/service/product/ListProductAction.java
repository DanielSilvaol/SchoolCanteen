package com.business.schoolcanteen.service.product;

import com.business.schoolcanteen.entity.Product;
import com.business.schoolcanteen.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListProductAction {
    private final ProductRepository productRepository;

    public List<Product> findAll(final String search) {
        return productRepository.findByNameLikeIgnoreCaseAndActiveTrue(search);
    }
}