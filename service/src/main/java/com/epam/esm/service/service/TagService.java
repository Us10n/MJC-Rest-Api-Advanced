package com.epam.esm.service.service;

import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.dto.TagDto;

/**
 * Interface that provides CRUD operations for Tags
 */
public interface TagService extends CRD<TagDto, Tag> {
    TagDto findWidelyUsedTagOfUserWithHighestCostOfAllOrders();
}
