package com.epam.esm.service.service;

import com.epam.esm.service.dto.Order;
import com.epam.esm.service.dto.OrderDetailDto;

import java.util.List;

public interface OrderService {
    OrderDetailDto create(Order object);

    List<OrderDetailDto> readAll(Integer page, Integer limit);

    OrderDetailDto readById(long id);

    OrderDetailDto readOrdersByUserId(long id, Integer page, Integer limit);
}
