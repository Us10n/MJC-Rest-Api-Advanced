package com.epam.esm.service.service;

import com.epam.esm.service.dto.Order;
import com.epam.esm.service.dto.OrderDetailDto;
import org.springframework.hateoas.PagedModel;

public interface OrderService {
    OrderDetailDto create(Order object);

    PagedModel<OrderDetailDto> readAll(Integer page, Integer limit);

    OrderDetailDto readById(long id);

    PagedModel<OrderDetailDto> readOrdersByUserId(long id, Integer page, Integer limit);
}
