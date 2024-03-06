package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import lombok.*;

import java.util.Map;


@Getter
public class Payment {
    String id;
    String method;
    Order order;
    String status;

    Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData){
        this.id = id;
        this.setStatus(status);


        if("Bank Transfer".equals(method) || "Voucher Code".equals(method)){
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }

        if(paymentData == null || paymentData.isEmpty()){
            throw new IllegalArgumentException("Payment data cannot be null or empty");
        }else{
            this.paymentData = paymentData;
            if ("Bank Transfer".equals(method)) {
                if (paymentData.get("bankName") == null || paymentData.get("bankName").isEmpty()) {
                    throw new IllegalArgumentException("Bank name cannot be empty for Bank Transfer");
                }
                if (paymentData.get("referenceCode") == null || paymentData.get("referenceCode").isEmpty()) {
                    throw new IllegalArgumentException("Reference code cannot be empty for Bank Transfer");
                }
            }
        }
    }

    public Payment(String id, Order order, String method, String status, Map<String, String> paymentData){
        this.id = id;
        this.order = order;
        this.method = method;
        setStatus(status);
        setPaymentData(paymentData);
    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void setPaymentData(Map<String, String> paymentData){
        if(paymentData.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            this.paymentData = paymentData;
        }
    }



}