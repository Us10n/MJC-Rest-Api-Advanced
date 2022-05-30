package com.epam.esm.service.service.impl;

import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.dto.converter.impl.TagConverter;
import com.epam.esm.service.service.TagService;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ResponseException;
import com.epam.esm.service.util.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final TagConverter tagConverter;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagConverter tagConverter) {
        this.tagDao = tagDao;
        this.tagConverter = tagConverter;
    }

    @Override
    public TagDto create(TagDto object) {
        if (TagValidator.isTagDtoValid(object)
                && !tagDao.findByName(object.getName()).isPresent()) {
            Tag tagModel = tagConverter.convertToEntity(object);
            Optional<Tag> createdTag = tagDao.create(tagModel);
            if (createdTag.isPresent()) {
                return tagConverter.convertToDto(createdTag.get());
            }
        }

        throw new ResponseException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<TagDto> readAll(Integer page, Integer limit) {
        return tagDao.findAll(page, limit)
                .stream()
                .map(tagConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto readById(long id) {
        Optional<Tag> optionalTag = tagDao.findById(id);
        if (!optionalTag.isPresent()) {
            throw new ResponseException(HttpStatus.NOT_FOUND);
        }
        return tagConverter.convertToDto(optionalTag.get());
    }

    @Override
    public void delete(long id) {
        if (!tagDao.findById(id).isPresent()) {
            throw new ResponseException(HttpStatus.NOT_FOUND);
        }
        tagDao.deleteById(id);
    }
}
