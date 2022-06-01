package com.epam.esm.service.service.impl;

import com.epam.esm.repository.dao.UserDao;
import com.epam.esm.repository.entity.User;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.dto.converter.impl.UserConverter;
import com.epam.esm.service.exception.NoSuchElementException;
import com.epam.esm.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.service.exception.ExceptionMessageKey.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserConverter converter, UserDao userDao) {
        this.userConverter = converter;
        this.userDao = userDao;
    }

    @Override
    public PagedModel<UserDto> readAll(Integer page, Integer limit) {
        List<UserDto> userDtos = userDao.findAll(page, limit)
                .stream()
                .map(userConverter::convertToDto)
                .collect(Collectors.toList());
        long totalNumberOfEntities = userDao.countAll();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(limit, page, totalNumberOfEntities);

        return PagedModel.of(userDtos, metadata);
    }

    @Override
    public UserDto readById(long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException(USER_NOT_FOUND);
        }

        return userConverter.convertToDto(optionalUser.get());
    }
}
