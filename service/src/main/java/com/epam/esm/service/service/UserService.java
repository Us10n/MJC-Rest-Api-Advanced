package com.epam.esm.service.service;

import com.epam.esm.service.dto.UserDto;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface UserService {
    PagedModel<UserDto> readAll(Integer page, Integer limit);
    UserDto readById(long id);
}
