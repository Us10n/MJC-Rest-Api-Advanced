package com.epam.esm.repository.dao;

import com.epam.esm.domain.entity.OrderDetail;

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

    long countAll();

    List<OrderDetail> findOrdersByUserId(long id, Integer page, Integer limit);
}
