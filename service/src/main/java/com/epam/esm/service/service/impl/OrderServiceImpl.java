package com.epam.esm.service.service.impl;

import com.epam.esm.repository.dao.GiftCertificateDao;
import com.epam.esm.repository.dao.OrderDao;
import com.epam.esm.repository.dao.UserDao;
import com.epam.esm.repository.entity.GiftCertificate;
import com.epam.esm.repository.entity.OrderDetail;
import com.epam.esm.repository.entity.User;
import com.epam.esm.service.dto.Order;
import com.epam.esm.service.dto.OrderDetailDto;
import com.epam.esm.service.dto.converter.impl.OrderConverter;
import com.epam.esm.service.exception.ExceptionHolder;
import com.epam.esm.service.exception.IncorrectParameterException;
import com.epam.esm.service.exception.NoSuchElementException;
import com.epam.esm.service.service.OrderService;
import com.epam.esm.service.util.handler.DateHandler;
import com.epam.esm.service.util.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.service.exception.ExceptionMessageKey.*;

@Component
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderConverter orderConverter;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderConverter orderConverter) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
    }

    @Override
    public OrderDetailDto create(Order order) {
        ExceptionHolder exceptionHolder = new ExceptionHolder();
        OrderValidator.isOrderValid(order,exceptionHolder);
        if (!exceptionHolder.getExceptionMessages().isEmpty()) {
            throw new IncorrectParameterException(exceptionHolder);
        }
        Optional<User> customer = userDao.findById(order.getUserId());
        if (!customer.isPresent()) {
            throw new NoSuchElementException(USER_NOT_FOUND);
        }
        Optional<GiftCertificate> requestedCertificate = giftCertificateDao.findById(order.getGiftCertificateId());
        if (!requestedCertificate.isPresent()) {
            throw new NoSuchElementException(GIFT_CERTIFICATE_NOT_FOUND);
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGiftCertificate(requestedCertificate.get());
        orderDetail.setUser(customer.get());
        orderDetail.setPrice(requestedCertificate.get().getPrice());
        orderDetail.setPurchaseTime(DateHandler.getCurrentDate());
        orderDao.create(orderDetail);

        return orderConverter.convertToDto(orderDetail);
    }

    @Override
    public List<OrderDetailDto> readAll(Integer page, Integer limit) {
        return orderDao.findAll(page, limit)
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto readById(long id) {
        Optional<OrderDetail> optionalOrder = orderDao.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new NoSuchElementException(ORDER_NOT_FOUND);
        }

        return orderConverter.convertToDto(optionalOrder.get());
    }

    @Override
    public List<OrderDetailDto> readOrdersByUserId(long id, Integer page, Integer limit) {
        Optional<User> optionalUser = userDao.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException(USER_NOT_FOUND);
        }

        return orderDao.findOrdersByUserId(id,page, limit)
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
