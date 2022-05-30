package com.epam.esm.service.dto.converter.impl;

import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.dto.converter.DtoEntityConverter;
import com.epam.esm.service.dto.TagDto;
import org.springframework.stereotype.Component;

@Component
public class TagConverter implements DtoEntityConverter<TagDto, Tag> {
    @Override
    public TagDto convertToDto(Tag object) {
        return new TagDto(object.getId(), object.getName());
    }

    @Override
    public Tag convertToEntity(TagDto object) {
        return new Tag(object.getTagId(), object.getName());
    }
}
