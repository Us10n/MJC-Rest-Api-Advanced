package com.epam.esm.service.service;

import com.epam.esm.repository.entity.GiftCertificate;
import com.epam.esm.service.criteria.GiftCertificateCriteria;
import com.epam.esm.service.dto.GiftCertificateDto;

import java.util.List;

/**
 * Interface that provides CRUD operations for GiftCertificates
 */
public interface GiftCertificateService extends CRUD<GiftCertificateDto, GiftCertificate> {
    /**
     * Read by criteria specified in criteria object.
     *
     * @param criteria object that contains read criterias
     * @return the list of {@link GiftCertificateDto} objects
     */
    List<GiftCertificateDto> readByCriteria(GiftCertificateCriteria criteria);
}
