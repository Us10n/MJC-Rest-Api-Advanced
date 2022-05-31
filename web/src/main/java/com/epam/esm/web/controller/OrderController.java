package com.epam.esm.web.controller;

import com.epam.esm.service.dto.Order;
import com.epam.esm.service.dto.OrderDetailDto;
import com.epam.esm.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderDetailDto> readAllOrders(@RequestParam(name = "page", defaultValue = "1") @Positive Integer page,
                                              @RequestParam(name = "limit", defaultValue = "10") @Positive Integer limit) {
        return orderService.readAll(page,limit);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public OrderDetailDto readOrderById(@PathVariable long id) {
        return orderService.readById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailDto createOrder(@RequestBody Order order){
        return orderService.create(order);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderDetailDto> readOrdersById(@PathVariable long id,
                                        @RequestParam(name = "page", defaultValue = "1") @Positive Integer page,
                                        @RequestParam(name = "limit", defaultValue = "10") @Positive Integer limit) {
        return orderService.readOrdersByUserId(id,page,limit);
    }
}
