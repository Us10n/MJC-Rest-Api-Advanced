package com.epam.esm.service.service.impl;

import com.epam.esm.domain.dto.Order;
import com.epam.esm.domain.dto.OrderDetailDto;
import com.epam.esm.domain.entity.GiftCertificate;
import com.epam.esm.domain.entity.OrderDetail;
import com.epam.esm.domain.entity.User;
import com.epam.esm.repository.dao.GiftCertificateDao;
import com.epam.esm.repository.dao.OrderDao;
import com.epam.esm.repository.dao.UserDao;
import com.epam.esm.service.converter.impl.OrderConverter;
import com.epam.esm.service.exception.ExceptionHolder;
import com.epam.esm.service.exception.IncorrectParameterException;
import com.epam.esm.service.exception.NoSuchElementException;
import com.epam.esm.service.service.OrderService;
import com.epam.esm.service.util.handler.DateHandler;
import com.epam.esm.service.util.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.service.exception.ExceptionMessageKey.*;

@Component
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final OrderConverter orderConverter;
    private final UserDao userDao;
    private final GiftCertificateDao giftCertificateDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderConverter orderConverter,
                            UserDao userDao, GiftCertificateDao giftCertificateDao) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
        this.userDao = userDao;
        this.giftCertificateDao = giftCertificateDao;
    }

    @Override
    public OrderDetailDto create(Order order) {
        ExceptionHolder exceptionHolder = new ExceptionHolder();
        OrderValidator.isOrderValid(order, exceptionHolder);
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
    public PagedModel<OrderDetailDto> readAll(Integer page, Integer limit) {
        List<OrderDetailDto> orderDetailDtos = orderDao.findAll(page, limit)
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
        long totalNumberOfEntities = orderDao.countAll();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(limit, page, totalNumberOfEntities);
        return PagedModel.of(orderDetailDtos, metadata);
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
    public PagedModel<OrderDetailDto> readOrdersByUserId(long id, Integer page, Integer limit) {
        Optional<User> optionalUser = userDao.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException(USER_NOT_FOUND);
        }

        List<OrderDetailDto> orderDetailDtos = orderDao.findOrdersByUserId(id, page, limit)
                .stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList());
        long totalNumberOfEntities = giftCertificateDao.countAll();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(limit, page, totalNumberOfEntities);
        return PagedModel.of(orderDetailDtos, metadata);
    }
}
