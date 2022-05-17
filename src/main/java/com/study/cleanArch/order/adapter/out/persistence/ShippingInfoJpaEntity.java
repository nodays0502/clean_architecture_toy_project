package com.study.cleanArch.order.adapter.out.persistence;

import com.study.cleanArch.order.domain.Address;
import com.study.cleanArch.order.domain.Receiver;
import com.study.cleanArch.order.domain.ShippingInfo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "shipping_info")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingInfoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_info_id")
    private Long id;

    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "detail_address")
    private String detailAddress;
    @Column(name = "request_message")
    private String requestMessage;

    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_phone")
    private String receiverPhone;


    public ShippingInfoJpaEntity(String zipCode, String detailAddress, String requestMessage,
        String receiverName, String receiverPhone) {
        this.zipCode = zipCode;
        this.detailAddress = detailAddress;
        this.requestMessage = requestMessage;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
    }

    public ShippingInfo mapToShippingInfo() {
        Receiver receiver = new Receiver(receiverName, receiverPhone);
        Address address = new Address(zipCode, detailAddress);
        return new ShippingInfo(receiver, requestMessage, address);
    }

    public ShippingInfo of() {
        return new ShippingInfo(new Receiver(zipCode, detailAddress), requestMessage,
            new Address(receiverName, receiverPhone));
    }
}