package com.business.schoolcanteen.service.sale;

import com.business.schoolcanteen.entity.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListSaleAction {
    private final MongoTemplate mongoTemplate;
    public List<Sale> findSalesByRegisterDate(LocalDate startDate, LocalDate endDate) {
        Date from = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant());
        Query query = new Query();
        query.addCriteria(
                Criteria.where("registerDate")
                        .gte(from)
                        .lt(to)
        );
        return mongoTemplate.find(query, Sale.class);
    }
}
