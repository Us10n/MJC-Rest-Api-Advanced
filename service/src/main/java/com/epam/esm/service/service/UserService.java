package com.epam.esm.service.service;

import com.epam.esm.service.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> readAll(Integer page, Integer limit);
    UserDto readById(long id);
}
