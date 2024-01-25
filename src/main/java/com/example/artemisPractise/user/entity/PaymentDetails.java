package com.example.artemisPractise.user.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class PaymentDetails implements Serializable {

    private Long id;

    private Long payerId;
    private String payerName;

    private String benefyId;
    private double txnAmount;

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "id=" + id +
                ", payerId=" + payerId +
                ", payerName='" + payerName + '\'' +
                ", benefyId='" + benefyId + '\'' +
                ", txnAmount=" + txnAmount +
                '}';
    }
}
