package com.epam.esm.service.service;

import com.epam.esm.domain.dto.UserDto;
import org.springframework.hateoas.PagedModel;

public interface UserService {
    PagedModel<UserDto> readAll(Integer page, Integer limit);
    UserDto readById(long id);
}
