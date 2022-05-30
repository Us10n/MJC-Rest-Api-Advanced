package com.epam.esm.repository.dao;

import com.epam.esm.repository.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

/**
 * Data access object that perform operations with {@link GiftCertificate} model in database
 */
public interface GiftCertificateDao extends Dao<GiftCertificate> {

    /**
     * Update {@link GiftCertificate} object instance with values specified in {@code giftCertificate}.
     *
     * @param giftCertificate contains new values
     * @return {@link Optional} instance of updated object
     */
    Optional<GiftCertificate> update(GiftCertificate giftCertificate);

    /**
     * Find {@link List<GiftCertificate>} of objects that matches criteria specified in {@code query}.
     *
     * @param query query with specified criteria
     * @return list of {@link GiftCertificate} objects that match with criteria
     */
    List<GiftCertificate> findByCriteria(String query);

    /**
     * Find object instance in database by name.
     *
     * @param name object name
     * @return {@link Optional Optional.ofNullable()} of found object.
     */
    Optional<GiftCertificate> findByName(String name);
}
