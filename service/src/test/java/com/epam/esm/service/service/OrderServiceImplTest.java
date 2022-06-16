package com.epam.esm.service.service;

import com.epam.esm.domain.entity.Order;
import com.epam.esm.domain.dto.OrderDetailDto;
import com.epam.esm.domain.entity.GiftCertificate;
import com.epam.esm.domain.entity.OrderDetail;
import com.epam.esm.domain.entity.User;
import com.epam.esm.repository.dao.GiftCertificateDao;
import com.epam.esm.repository.dao.OrderDao;
import com.epam.esm.repository.dao.UserDao;
import com.epam.esm.service.config.ServiceConfigTest;
import com.epam.esm.service.converter.impl.OrderConverter;
import com.epam.esm.service.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootApplication
class OrderServiceImplTest {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GiftCertificateDao giftCertificateDao;

    LocalDateTime sampleDate = LocalDateTime.parse("2022-04-11T10:00:11.156");
    User sampleUser = new User(1, "Nick", null);
    GiftCertificate sampleCertificate = new GiftCertificate(
            1, "test1", "test1", 1.1, 1, sampleDate, sampleDate, null
    );
    private OrderService orderService;
    private List<OrderDetail> orders;
    private List<OrderDetailDto> orderDtos;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        orders.add(new OrderDetail(1, 1.1, sampleDate, sampleUser, sampleCertificate));
        orderDtos = new ArrayList<>();
        orderDtos.add(new OrderDetailDto(1, 1.1, sampleDate, 1, 1));

        Mockito.when(userDao.findById(1)).thenReturn(Optional.of(sampleUser));
        Mockito.when(giftCertificateDao.findById(1)).thenReturn(Optional.of(sampleCertificate));
        Mockito.when(orderDao.create(Mockito.any(OrderDetail.class))).thenReturn(orders.get(0));
        Mockito.when(orderDao.countAll()).thenReturn(1L);
        Mockito.when(orderDao.findAll(1,10)).thenReturn(orders);
        Mockito.when(orderDao.findById(1)).thenReturn(Optional.of(orders.get(0)));
        Mockito.when(giftCertificateDao.countAll()).thenReturn(1L);
        Mockito.when(orderDao.findOrdersByUserId(1, 1, 10)).thenReturn(orders);

        orderService = new OrderServiceImpl(orderDao, new OrderConverter(), userDao, giftCertificateDao);
    }

    @Test
    void create() {
        Order order = new Order(1, 1);
        OrderDetailDto actual = orderService.create(order);
        OrderDetailDto expected = new OrderDetailDto(1, 1.1, sampleDate, 1, 1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        List<OrderDetailDto> actual = new ArrayList<>(orderService.readAll(1, 10).getContent());
        List<OrderDetailDto> expected = orderDtos;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readById() {
        OrderDetailDto actual = orderService.readById(1);
        OrderDetailDto expected=new OrderDetailDto(1,1.1,sampleDate,1,1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readOrdersByUserId() {
        List<OrderDetailDto> actual = new ArrayList<>(orderService.readOrdersByUserId(1,1,10).getContent());
        List<OrderDetailDto> expected = orderDtos;

        Assertions.assertEquals(expected, actual);
    }
}