package com.business.schoolcanteen.entity;


import com.business.schoolcanteen.entity.enums.PaymentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double total;
    private double productPrice;
    private LocalDateTime registerDate;

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentType;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;
}