package com.business.schoolcanteen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "productHistory")
public class ProductHistory {
    @Id
    private String id;
    private String productId;
    private String description;
    private LocalDateTime registerDate;
}
