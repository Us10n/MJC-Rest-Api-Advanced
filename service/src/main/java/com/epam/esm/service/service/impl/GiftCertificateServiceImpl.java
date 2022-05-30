package com.epam.esm.service.service.impl;

import com.epam.esm.repository.constants.ColumnNames;
import com.epam.esm.repository.dao.GiftCertificateDao;
import com.epam.esm.repository.dao.TagDao;
import com.epam.esm.repository.entity.GiftCertificate;
import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.dto.converter.impl.GiftCertificateConverter;
import com.epam.esm.service.dto.converter.impl.TagConverter;
import com.epam.esm.service.criteria.GiftCertificateCriteria;
import com.epam.esm.service.dto.GiftCertificateDto;
import com.epam.esm.service.exception.ResponseException;
import com.epam.esm.service.service.GiftCertificateService;
import com.epam.esm.service.util.builder.GiftCertificateQueryBuilder;
import com.epam.esm.service.util.handler.DateHandler;
import com.epam.esm.service.util.validator.GiftCertificateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private GiftCertificateDao giftCertificateDao;
    private TagDao tagDao;
    @Autowired
    private GiftCertificateConverter giftCertificateConverter;
    @Autowired
    private TagConverter tagConverter;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagDao tagDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public GiftCertificateDto create(GiftCertificateDto object) {
        if (GiftCertificateValidator.isGiftCertificateDtoValid(object)
                && !giftCertificateDao.findByName(object.getName()).isPresent()) {
            GiftCertificate certificateModel = giftCertificateConverter.convertToEntity(object);
            LocalDateTime currentDate = DateHandler.getCurrentDate();
            certificateModel.setCreateDate(currentDate);
            certificateModel.setLastUpdateDate(currentDate);

            List<Tag> newTags = new ArrayList<>(certificateModel.getTags());
            List<Tag> tagsToPersist = createTagListToPersist(newTags);
            certificateModel.setTags(new HashSet<>(tagsToPersist));
            Optional<GiftCertificate> createdCertificateOptional = giftCertificateDao.create(certificateModel);
            if (createdCertificateOptional.isPresent()) {
                return giftCertificateConverter.convertToDto(createdCertificateOptional.get());
            }
        }
        throw new ResponseException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<GiftCertificateDto> readAll(Integer page, Integer limit) {
        return giftCertificateDao.findAll(page, limit)
                .stream()
                .map(giftCertificateConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto readById(long id) {
        Optional<GiftCertificate> optionalGitCertificate = giftCertificateDao.findById(id);
        if (!optionalGitCertificate.isPresent()) {
            throw new ResponseException(HttpStatus.NOT_FOUND);
        }
        return giftCertificateConverter.convertToDto(optionalGitCertificate.get());
    }

    @Override
    public List<GiftCertificateDto> readByCriteria(GiftCertificateCriteria criteria) {
        if (criteria.getSortBy() != null) {
            if (!(GiftCertificateCriteria.NAME.equalsIgnoreCase(criteria.getSortBy())
                    || GiftCertificateCriteria.DATE.equalsIgnoreCase(criteria.getSortBy()))) {
                throw new ResponseException(HttpStatus.BAD_REQUEST, new Object[]{"sort", "name", "date"});
            }
        }
        if (criteria.getSortOrder() != null) {
            if (!(GiftCertificateCriteria.ASC.equalsIgnoreCase(criteria.getSortOrder())
                    || GiftCertificateCriteria.DESC.equalsIgnoreCase(criteria.getSortOrder()))) {
                throw new ResponseException(HttpStatus.BAD_REQUEST, new Object[]{"order", "asc", "desc"});
            }
        }

        String findQueryByCriteria= GiftCertificateQueryBuilder.buildGetQueryByCriteria(criteria);
        List<GiftCertificate> foundCertificates = giftCertificateDao.findByCriteria(findQueryByCriteria);
        return foundCertificates.stream()
                .map(giftCertificateConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto object) {
        if (object != null) {
            //copy values from existing certificate
            GiftCertificateDto currentCertificate = readById(object.getGiftCertificateId());
            if (object.getName() == null) object.setName(currentCertificate.getName());
            if (object.getDescription() == null) object.setDescription(currentCertificate.getDescription());
            if (object.getDuration() == null) object.setDuration(currentCertificate.getDuration());
            if (object.getPrice() == null) object.setPrice(currentCertificate.getPrice());
            if (object.getCreateDate() == null) object.setCreateDate(currentCertificate.getCreateDate());
            if (object.getTags() == null) object.setTags(currentCertificate.getTags());
            object.setLastUpdateDate(DateHandler.getCurrentDate());

            if (GiftCertificateValidator.isGiftCertificateDtoValid(object)) {
                GiftCertificate certificateModel;
                try {
                    certificateModel = giftCertificateConverter.convertToEntity(object);
                } catch (DateTimeParseException e) {
                    throw new ResponseException(HttpStatus.BAD_REQUEST, new Object[]{object.getCreateDate()});
                }
                List<Tag> newTags = object.getTags().stream()
                        .map(tagConverter::convertToEntity)
                        .collect(Collectors.toList());
                List<Tag> tagsToPersist = createTagListToPersist(newTags);
                certificateModel.setTags(new HashSet<>(tagsToPersist));
                Optional<GiftCertificate> updatedCertificate = giftCertificateDao.update(certificateModel);
                if (updatedCertificate.isPresent()) {
                    return giftCertificateConverter.convertToDto(updatedCertificate.get());
                }
            }
        }
        throw new ResponseException(HttpStatus.NOT_FOUND);
    }

    @Override
    public void delete(long id) {
        if (!giftCertificateDao.findById(id).isPresent()) {
            throw new ResponseException(HttpStatus.NOT_FOUND);
        }
        giftCertificateDao.deleteById(id);
    }

    private List<Tag> createTagListToPersist(List<Tag> tags) {
        List<Tag> uniqueTags = tags.stream()
                .distinct()
                .collect(Collectors.toList());
        List<Tag> tagsToPersist = new ArrayList<>();
        uniqueTags.forEach(newTag -> {
            Optional<Tag> foundTag = tagDao.findByName(newTag.getName());
            if (foundTag.isPresent()) {
                tagsToPersist.add(foundTag.get());
            } else {
                tagsToPersist.add(newTag);
            }
        });
        return tagsToPersist;
    }
}
