package com.epam.esm.service.util.validator;

import com.epam.esm.repository.entity.Tag;
import com.epam.esm.service.dto.GiftCertificateDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@UtilityClass
public class GiftCertificateValidator {
    private static final Integer MIN_LENGTH = 1;
    private static final Double MIN_PRICE = 0.1;
    private static final Double MAX_PRICE = 10000.0;
    private static final Integer MIN_DURATION = 1;
    private static final Integer MAX_DURATION = 28;

    public boolean isNameValid(String name) {
        return name != null && name.length() >= MIN_LENGTH;
    }

    public boolean isDescriptionValid(String description) {
        return description != null && description.length() >= MIN_LENGTH;
    }

    public boolean isPriceValid(Double price) {
        return price != null && price >= MIN_PRICE && price <= MAX_PRICE;
    }

    public boolean isDurationValid(Integer duration) {
        return duration != null && duration >= MIN_DURATION && duration <= MAX_DURATION;
    }

    public boolean isTagsValid(List<Tag> tags) {
        return tags != null && ((tags.stream().map(Tag::getName).allMatch(TagValidator::isNameValid) || tags.isEmpty()));
    }

    public boolean isDateValid(String date) {
        try {
            LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isGiftCertificateDtoValid(GiftCertificateDto giftCertificateDto) {
        return giftCertificateDto != null
                && isNameValid(giftCertificateDto.getName())
                && isDescriptionValid(giftCertificateDto.getDescription())
                && isPriceValid(giftCertificateDto.getPrice())
                && isDurationValid(giftCertificateDto.getDuration())
                && isDateValid(giftCertificateDto.getCreateDate().toString())
                && isDateValid(giftCertificateDto.getLastUpdateDate().toString())
                && giftCertificateDto.getTags().stream().allMatch(TagValidator::isTagDtoValid);
    }
}
