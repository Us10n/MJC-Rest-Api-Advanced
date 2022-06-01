package com.epam.esm.service.util.validator;

import com.epam.esm.domain.criteria.GiftCertificateCriteria;
import com.epam.esm.service.exception.ExceptionHolder;
import lombok.experimental.UtilityClass;

import static com.epam.esm.service.exception.ExceptionMessageKey.*;

@UtilityClass
public class GiftCertificateCriteriaValidator {
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final int MIN_LENGTH = 1;

    public boolean isSortOrderValid(String order) {
        return order.equalsIgnoreCase(ASC) || order.equalsIgnoreCase(DESC);
    }

    public boolean isSortColumnValid(String column) {
        return column.equalsIgnoreCase(NAME) || column.equalsIgnoreCase(DATE);
    }

    public boolean isPartNameValid(String partName) {
        return partName.length() >= MIN_LENGTH;
    }

    public boolean isPartDescriptionValid(String partDescription) {
        return partDescription.length() >= MIN_LENGTH;
    }

    public void isCriteriaValid(GiftCertificateCriteria criteria, ExceptionHolder holder) {
        if (criteria == null) {
            holder.addException(NULL_PASSED, GiftCertificateCriteria.class);
            return;
        }
        if (criteria.getSortBy() != null && !isSortColumnValid(criteria.getSortBy())) {
            holder.addException(BAD_SORT_COLUMN, criteria.getSortBy(), NAME, DATE);
        }
        if (criteria.getSortOrder() != null && !isSortOrderValid(criteria.getSortOrder())) {
            holder.addException(BAD_SORT_ORDER, criteria.getSortOrder(), ASC, DESC);
        }
        if (criteria.getPartName() != null && !isPartNameValid(criteria.getPartName())) {
            holder.addException(BAD_PART_NAME, criteria.getPartName());
        }
        if (criteria.getPartDesc() != null && !isPartDescriptionValid(criteria.getPartDesc())) {
            holder.addException(BAD_PART_DESCRIPTION, criteria.getPartDesc());
        }
    }
}
