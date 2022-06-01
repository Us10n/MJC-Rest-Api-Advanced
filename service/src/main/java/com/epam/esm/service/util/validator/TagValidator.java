package com.epam.esm.service.util.validator;

import com.epam.esm.domain.dto.TagDto;
import com.epam.esm.service.exception.ExceptionHolder;
import lombok.experimental.UtilityClass;

import static com.epam.esm.service.exception.ExceptionMessageKey.BAD_TAG_NAME;
import static com.epam.esm.service.exception.ExceptionMessageKey.NULL_PASSED;

@UtilityClass
public class TagValidator {
    private static final int MIN_LENGTH = 1;

    public boolean isNameValid(String name) {
        return name != null && name.length() >= MIN_LENGTH;
    }

    public void isTagDtoValid(TagDto tagDto, ExceptionHolder exceptionHolder) {
        if (tagDto == null) {
            exceptionHolder.addException(NULL_PASSED, TagDto.class);
            return;
        }
        if (!isNameValid(tagDto.getName())) {
            exceptionHolder.addException(BAD_TAG_NAME, tagDto.getName());
        }
    }
}
