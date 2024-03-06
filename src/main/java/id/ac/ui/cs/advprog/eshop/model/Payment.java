package id.ac.ui.cs.advprog.eshop.model;

import lombok.*;

import java.util.Map;


@Getter
public class Payment {
    String id;
    String method;
    Order order;

    @Setter
    String status;

    Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData){
    }

    public Payment(String id, Order order, String method, String status, Map<String, String> paymentData){
    }


}