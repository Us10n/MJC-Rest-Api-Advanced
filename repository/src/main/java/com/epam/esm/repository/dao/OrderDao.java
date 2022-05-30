package com.epam.esm.repository.dao;

import com.epam.esm.repository.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao{
    /**
     * Create {@link T} object instance in database.
     *
     * @param object object to create in database
     * @return {@link Optional Optional.ofNullable()} of created object
     */
    Optional<Order> create(Order object);

    /**
     * Find object instance in database by id.
     *
     * @param id object id
     * @return {@link Optional Optional.ofNullable()} of found object.
     */
    Optional<Order> findById(long id);

    /**
     * Find all object instances in database.
     *
     * @return list of found objects
     */
    List<Order> findAll();
}
