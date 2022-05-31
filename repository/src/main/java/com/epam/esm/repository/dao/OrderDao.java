package com.epam.esm.repository.dao;

import com.epam.esm.repository.entity.OrderDetail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    /**
     * Create {@link OrderDetail} object instance in database.
     *
     * @param object object to create in database
     * @return {@link Optional Optional.ofNullable()} of created object
     */
    OrderDetail create(OrderDetail object);

    /**
     * Find object instance in database by id.
     *
     * @param id object id
     * @return {@link Optional Optional.ofNullable()} of found object.
     */
    Optional<OrderDetail> findById(long id);

    List<OrderDetail> findAll(int page, int limit);

    List<OrderDetail> findOrdersByUserId(long id, Integer page, Integer limit);
}
