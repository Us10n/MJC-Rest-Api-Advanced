package com.epam.esm.service.util.handler;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Class that provides current date time
 */
@UtilityClass
public class DateHandler {
    /**
     * Gets current date.
     *
     * @return current date Time
     */
    public LocalDateTime getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime;
    }
}
