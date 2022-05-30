package com.epam.esm.service.dto.converter.impl;

import com.epam.esm.repository.entity.User;
import com.epam.esm.service.dto.converter.DtoEntityConverter;
import com.epam.esm.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements DtoEntityConverter<UserDto, User> {
    @Override
    public UserDto convertToDto(User object) {
        return null;
    }

    @Override
    public User convertToEntity(UserDto object) {
        return null;
    }
}
