package com.epam.esm.repository.dao;

import com.epam.esm.repository.entity.GiftCertificate;
import com.epam.esm.repository.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Data access object that perform operations with {@link Tag} model in database
 */
public interface TagDao extends Dao<Tag> {
    /**
     * Find object instance in database by name.
     *
     * @param name object name
     * @return {@link Optional Optional.ofNullable()} of found object.
     */
    Optional<Tag> findByName(String name);

    Optional<Tag> findWidelyUsedTagOfUserWithHighestCostOfAllOrders();
}
