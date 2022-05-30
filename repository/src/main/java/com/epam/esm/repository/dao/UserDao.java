package com.epam.esm.repository.dao;

import com.epam.esm.repository.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * Find object instance in database by id.
     *
     * @param id object id
     * @return {@link Optional Optional.ofNullable()} of found object.
     */
    Optional<User> findById(long id);

    List<User> findAll(int page, int limit);}
