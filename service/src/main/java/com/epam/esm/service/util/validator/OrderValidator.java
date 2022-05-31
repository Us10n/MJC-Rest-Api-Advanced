package com.epam.esm.service.util.validator;

import com.epam.esm.service.dto.Order;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ExceptionHolder;
import lombok.experimental.UtilityClass;

import static com.epam.esm.service.exception.ExceptionMessageKey.*;

@UtilityClass
public class OrderValidator {
    public boolean isUserIdValid(long userId) {
        return userId > 0;
    }

    public boolean isGiftCertificateIdValid(long certId) {
        return certId > 0;
    }

    public void isOrderValid(Order order, ExceptionHolder holder){
        if (order == null) {
            holder.addException(NULL_PASSED, TagDto.class);
            return;
        }
        if (!isUserIdValid(order.getUserId())) {
            holder.addException(BAD_ORDER_USER_ID, order.getUserId());
        }
        if (!isGiftCertificateIdValid(order.getGiftCertificateId())) {
            holder.addException(BAD_ORDER_GIFT_CERTIFICATE_ID, order.getGiftCertificateId());
        }
    }
}
