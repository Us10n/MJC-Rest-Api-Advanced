package com.epam.esm.service.dto.converter.impl;

import com.epam.esm.repository.entity.User;
import com.epam.esm.service.dto.converter.DtoEntityConverter;
import com.epam.esm.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter implements DtoEntityConverter<UserDto, User> {

    @Autowired
    OrderConverter orderConverter;

    @Override
    public UserDto convertToDto(User object) {
        UserDto userDto = new UserDto();
        userDto.setUserId(object.getId());
        userDto.setUserName(object.getName());
        userDto.setOrders(object.getOrders().stream()
                .map(orderConverter::convertToDto)
                .collect(Collectors.toList()));
        return userDto;
    }

    @Override
    public User convertToEntity(UserDto object) {
        User user = new User();
        user.setId(object.getUserId());
        user.setName(object.getUserName());
        user.setOrders(object.getOrders().stream()
                .map(orderConverter::convertToEntity)
                .collect(Collectors.toList()));
        return user;
    }
}
