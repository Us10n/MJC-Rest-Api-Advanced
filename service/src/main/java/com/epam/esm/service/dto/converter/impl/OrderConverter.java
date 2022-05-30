package com.epam.esm.service.dto.converter.impl;

import com.epam.esm.repository.entity.Order;
import com.epam.esm.service.dto.converter.DtoEntityConverter;
import com.epam.esm.service.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements DtoEntityConverter<OrderDto, Order> {
    @Override
    public OrderDto convertToDto(Order object) {
        return null;
    }

    @Override
    public Order convertToEntity(OrderDto object) {
        return null;
    }
}
