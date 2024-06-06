package com.business.schoolcanteen.entity;


import com.business.schoolcanteen.entity.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sale")
public class Sale {
    @Id
    private String id;
    private int quantity;
    private double total;
    private double productPrice;
    private LocalDateTime registerDate;

    private PaymentTypeEnum paymentType;

    @DBRef
    private Product product;

    @DBRef
    private Customer customer;
}
