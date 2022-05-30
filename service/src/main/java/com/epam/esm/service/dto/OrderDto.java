package com.epam.esm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "orders")
public class OrderDto {
    private long orderId;
    private Double price;
    private LocalDateTime purchaseTime;
    private UserDto user;
    private GiftCertificateDto giftCertificateDto;
}
