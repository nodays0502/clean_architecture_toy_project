package com.study.cleanArch.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ShippingInfo {
    private Receiver receiver;
    private String requestMessage;
    private Address address;
}
