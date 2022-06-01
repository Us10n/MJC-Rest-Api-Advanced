package com.epam.esm.repository.creator;

import com.epam.esm.domain.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.entity.GiftCertificate;
import lombok.experimental.UtilityClass;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class GiftCertificateQueryCreator {
    private static final String PERCENT = "%";
    private static final String NAME = "name";
    private static final String ASC = "ASC";
    private static final String DESC = "DESC";
    private static final String DESCRIPTION = "description";
    private static final String CREATE_DATE = "createDate";
    private static final String TAGS_TABLE = "tags";

    public CriteriaQuery<GiftCertificate> buildGetQueryByCriteria(GiftCertificateCriteria criteria, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GiftCertificate.class);
        List<Predicate> restrictions = new ArrayList<>();

        restrictions.addAll(addPartOfName(criteria, criteriaBuilder, giftCertificateRoot));
        restrictions.addAll(addPartOfDescription(criteria, criteriaBuilder, giftCertificateRoot));
        restrictions.addAll(addTagNames(criteria, criteriaBuilder, giftCertificateRoot));
        criteriaQuery.select(giftCertificateRoot).where(restrictions.toArray(new Predicate[]{}));

        addSortByName(criteria, criteriaBuilder, criteriaQuery, giftCertificateRoot);
        addSortByCreateDate(criteria, criteriaBuilder, criteriaQuery, giftCertificateRoot);

        return criteriaQuery;
    }

    private List<Predicate> addPartOfName(GiftCertificateCriteria criteria, CriteriaBuilder criteriaBuilder,
                                          Root<GiftCertificate> root) {
        List<Predicate> restrictions = new ArrayList<>();
        String partName = criteria.getPartName();

        if (partName != null) {
            restrictions.add(criteriaBuilder.like(root.get(NAME), PERCENT + partName + PERCENT));
        }
        return restrictions;
    }

    private List<Predicate> addPartOfDescription(GiftCertificateCriteria criteria, CriteriaBuilder criteriaBuilder,
                                                 Root<GiftCertificate> root) {
        List<Predicate> restrictions = new ArrayList<>();
        String partDescription = criteria.getPartDesc();

        if (partDescription != null) {
            restrictions.add(criteriaBuilder.like(root.get(DESCRIPTION), PERCENT + partDescription + PERCENT));
        }
        return restrictions;
    }

    private void addSortByName(GiftCertificateCriteria criteria, CriteriaBuilder criteriaBuilder,
                               CriteriaQuery<GiftCertificate> criteriaQuery, Root<GiftCertificate> root) {
        String sortOrder = criteria.getSortOrder();

        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase(ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(NAME)));
            }
            if (sortOrder.equalsIgnoreCase(DESC)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(NAME)));
            }
        }
    }

    private void addSortByCreateDate(GiftCertificateCriteria criteria, CriteriaBuilder criteriaBuilder,
                                     CriteriaQuery<GiftCertificate> criteriaQuery, Root<GiftCertificate> root) {
        String sortOrder = criteria.getSortOrder();

        if (sortOrder != null) {
            if (sortOrder.equalsIgnoreCase(ASC)) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(CREATE_DATE)));
            }
            if (sortOrder.equalsIgnoreCase(DESC)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(CREATE_DATE)));
            }
        }
    }

    private List<Predicate> addTagNames(GiftCertificateCriteria criteria, CriteriaBuilder criteriaBuilder,
                                        Root<GiftCertificate> root) {
        List<Predicate> restrictions = new ArrayList<>();

        Set<String> tagNames = criteria.getTagNames();
        if (tagNames != null) {
            restrictions = tagNames.stream()
                    .map(tagName ->
                            criteriaBuilder.equal(root.join(TAGS_TABLE).get(NAME), tagName))
                    .collect(Collectors.toList());
        }
        return restrictions;
    }


}
