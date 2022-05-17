package com.study.cleanArch.order.domain;

import javax.persistence.Embeddable;

public enum OrderState {
    NEW,WAITING_FOR_DELIVERY, CANCEL, SHIPPING, DELIVERY_COMPLETED
}
