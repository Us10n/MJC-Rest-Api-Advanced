package com.epam.esm.service.service;

import com.epam.esm.domain.dto.UserDto;
import com.epam.esm.domain.entity.User;
import com.epam.esm.repository.dao.UserDao;
import com.epam.esm.service.config.ServiceConfigTest;
import com.epam.esm.service.converter.impl.OrderConverter;
import com.epam.esm.service.converter.impl.UserConverter;
import com.epam.esm.service.service.impl.UserServiceImpl;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceConfigTest.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootApplication
class UserServiceImplTest {

    @Autowired
    private UserDao userDao;
    private UserService userService;
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        userList.add(new User(1, "Rick", null));
        userList.add(new User(2, "Sam", null));

        Mockito.when(userDao.findById(1)).thenReturn(Optional.of(userList.get(0)));
        Mockito.when(userDao.findAll(1, 10)).thenReturn(userList);

        OrderConverter orderConverter = new OrderConverter();
        UserConverter userConverter = new UserConverter(orderConverter);
        userService = new UserServiceImpl(userConverter, userDao);
    }

    @Test
    void readAll() {
        UserDto actual = new ArrayList<UserDto>(userService.readAll(1, 10).getContent()).get(0);
        UserDto expected = new UserDto(1, "Rick", null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readById() {
        UserDto actual = userService.readById(1);
        UserDto expected = new UserDto(1, "Rick", null);

        Assertions.assertEquals(expected, actual);
    }
}