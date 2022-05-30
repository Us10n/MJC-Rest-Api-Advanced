package com.epam.esm.service.util.validator;

import com.epam.esm.service.dto.TagDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TagValidator {
    private static final int MIN_LENGTH = 1;

    public boolean isNameValid(String name) {
        return name != null && name.length() >= MIN_LENGTH;
    }

    public boolean isTagDtoValid(TagDto tagDto) {
        return tagDto != null && isNameValid(tagDto.getName());
    }
}
