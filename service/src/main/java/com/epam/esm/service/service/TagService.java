package com.epam.esm.service.service;

import com.epam.esm.domain.dto.TagDto;
import com.epam.esm.domain.entity.Tag;

/**
 * Interface that provides CRUD operations for Tags
 */
public interface TagService extends CRD<TagDto, Tag> {
    TagDto findWidelyUsedTagOfUserWithHighestCostOfAllOrders();
}
