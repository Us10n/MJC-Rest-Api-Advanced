package com.epam.esm.service.service.impl;

import com.epam.esm.repository.dao.UserDao;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.repository.entity.User;
import com.epam.esm.service.dto.converter.impl.UserConverter;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ResponseException;
import com.epam.esm.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserConverter userConverter;
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserConverter converter, UserDao userDao) {
        this.userConverter = converter;
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> readAll(Integer page, Integer limit) {
        return userDao.findAll(page, limit)
                .stream()
                .map(userConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto readById(long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseException(HttpStatus.NOT_FOUND);
        }
        return userConverter.convertToDto(optionalUser.get());
    }
}
