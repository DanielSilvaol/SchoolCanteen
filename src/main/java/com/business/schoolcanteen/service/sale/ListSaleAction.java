package com.business.schoolcanteen.service.sale;

import com.business.schoolcanteen.entity.Sale;
import com.business.schoolcanteen.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListSaleAction {
    private final SaleRepository saleRepository;
    public List<Sale> findAll(final LocalDate date) {
        return saleRepository.findSalesByRegisterDate(date);
    }
}
