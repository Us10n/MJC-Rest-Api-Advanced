package com.epam.esm.service.exception;

public final class ExceptionMessageKey {

    public static final String NULL_PASSED = "error.nullPassed";

    public static final String BAD_SORT_ORDER = "criteria.sort.badOrder";
    public static final String BAD_SORT_COLUMN = "criteria.sort.badColumn";
    public static final String BAD_PART_NAME = "criteria.badPartName";
    public static final String BAD_PART_DESCRIPTION = "criteria.badPartDescription";

    public static final String BAD_GIFT_CERTIFICATE_NAME = "certificate.badName";
    public static final String BAD_GIFT_CERTIFICATE_DESCRIPTION = "certificate.badDescription";
    public static final String BAD_GIFT_CERTIFICATE_PRICE = "certificate.badPrice";
    public static final String BAD_GIFT_CERTIFICATE_DURATION = "certificate.badDuration";
    public static final String BAD_GIFT_CERTIFICATE_CREATE_DATE = "certificate.badCreateDate";
    public static final String BAD_GIFT_CERTIFICATE_UPDATE_DATE = "certificate.badUpdateDate";
    public static final String GIFT_CERTIFICATE_MUST_CONTAIN_TAGS = "certificate.badTagsAmount";
    public static final String GIFT_CERTIFICATE_EXIST = "certificate.alreadyExist";
    public static final String GIFT_CERTIFICATE_NOT_FOUND = "certificate.notFound";

    public static final String BAD_TAG_NAME = "tag.badName";
    public static final String TAG_EXIST = "tag.alreadyExist";
    public static final String TAG_NOT_FOUND = "tag.notFound";

    public static final String USER_NOT_FOUND = "user.notFound";

    public static final String BAD_ORDER_USER_ID = "order.badUserId";
    public static final String BAD_ORDER_GIFT_CERTIFICATE_ID = "order.badCertificateId";
    public static final String ORDER_NOT_FOUND = "order.notFound";

}
