package com.epam.esm.service.util.builder;

import com.epam.esm.repository.constants.ColumnNames;
import com.epam.esm.service.criteria.GiftCertificateCriteria;
import com.epam.esm.service.dto.TagDto;
import com.jayway.jsonpath.Criteria;
import lombok.experimental.UtilityClass;

import java.util.Iterator;

@UtilityClass
public class GiftCertificateQueryBuilder {
    private static final String FIND_ALL_QUERY = "SELECT DISTINCT gift_certificates.id, gift_certificates.name, gift_certificates.description, gift_certificates.price, " +
            "gift_certificates.duration, gift_certificates.create_date, gift_certificates.last_update_date " +
            "FROM module.gift_certificates " +
            "JOIN module.gift_certificate_tags ON gift_certificates.id=gift_certificate_tags.gift_certificate_id " +
            "JOIN module.tags ON gift_certificate_tags.tag_id=tags.id ";
    private static final String FIND_BY_TAG_NAME_QUERY = " tags.name='%s' ";
    private static final String FIND_BY_PART_NAME_QUERY = " gift_certificates.name like concat('%s', '%%') ";
    private static final String FIND_BY_PART_DESC_QUERY = " gift_certificates.description like concat('%s', '%%') ";
    private static final String WHERE = "where";
    private static final String AND = "and";
    private static final String ORDER_BY = "order by %s %s";

    public String buildGetQueryByCriteria(GiftCertificateCriteria criteria) {
        StringBuilder queryBuilder = new StringBuilder(FIND_ALL_QUERY);
        if (criteria.getTagNames() != null || criteria.getPartName() != null || criteria.getPartDesc() != null) {
            queryBuilder.append(WHERE);
        }
        if (criteria.getTagNames() != null) {
            Iterator<String> tagsIterator = criteria.getTagNames().iterator();
            while (tagsIterator.hasNext()) {
                final String byTag = String.format(FIND_BY_TAG_NAME_QUERY, tagsIterator.next());
                queryBuilder.append(byTag);
                if (tagsIterator.hasNext()) {
                    queryBuilder.append(AND);
                }
            }
        }
        if (criteria.getPartName() != null) {
            if (criteria.getTagNames() != null) {
                queryBuilder.append(AND);
            }
            final String partName = String.format(FIND_BY_PART_NAME_QUERY, criteria.getPartName());
            queryBuilder.append(partName);
        }
        if (criteria.getPartDesc() != null) {
            if (criteria.getTagNames() != null || criteria.getPartName() != null) {
                queryBuilder.append(AND);
            }
            final String partDesc = String.format(FIND_BY_PART_DESC_QUERY, criteria.getPartDesc());
            queryBuilder.append(partDesc);
        }
        if (criteria.getSortBy() != null) {
            String sortColumn = ColumnNames.NAME.equalsIgnoreCase(criteria.getSortBy()) ? ColumnNames.NAME : ColumnNames.CREATE_DATE;
            String order = criteria.getSortOrder() != null ? criteria.getSortOrder() : GiftCertificateCriteria.ASC;
            final String orderByQuery = String.format(ORDER_BY, sortColumn, order);
            queryBuilder.append(orderByQuery);
        }
        return queryBuilder.toString();
    }
}
