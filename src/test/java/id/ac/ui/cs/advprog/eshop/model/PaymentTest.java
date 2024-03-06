package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    private List<Product> products;
    private List<Order> orders;
//    private Map<String, String> paymentData;

    @BeforeEach
    void setUp(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Cabai Merah");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        product2.setProductName("Cabai Hijau");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("acp58g99-1c39-450h-8860-71af6af63cd7",
                products, 1708570000L, "Safira Sederajat");
        orders.add(order2);
    }

    @Test
    void testCreateEmptyPayment(){
        assertThrows(IllegalArgumentException.class, () ->{
            Payment payment = new Payment("", "", "", new HashMap<>());
        });
    }

    @Test
    void testCreatePaymentWithStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7",
                orders.get(1), "Voucher Code", "SUCCESS", paymentData);

        assertEquals("eb558e9f-1c39-460e-7760-33af6af63bd7", payment.getId());
        assertEquals(orders.get(1), payment.getOrder());
        assertEquals("Voucher Code", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "voucherCode",
                    "INVALID_STATUS", paymentData);});
    }

    @Test
    void testCreatePaymentWithNoStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, ()-> {
                new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "voucherCode",
                        " ", paymentData);});
    }

    @Test
    void testCreatePaymentWithEmptyPaymentData(){
        assertThrows(IllegalArgumentException.class, ()-> {
                new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "voucherCode",
                        "GAGAL", Map.of());});
    }

    @Test
    void testSetStatusToInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7",
                orders.get(1), "Voucher Code", "SUCCESS", paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
                payment.setStatus("INVALID_STATUS");});
    }

    @Test
    void testCreatePaymentVoucherWithInvalidLenght(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234AC5678");

        assertThrows(IllegalArgumentException.class, ()-> {
                new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "voucherCode",
                        "SUCCESS", paymentData);});
    }

    @Test
    void testCreatePaymentVoucherInvalidStartCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOk1234ABC5678");
        assertThrows(IllegalArgumentException.class, ()-> {
                new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "voucherCode",
                        "SUCCESS", paymentData);});
    }

    @Test
    void testCreatePaymentVoucherInvalidNumerical(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOk1234ABC567X");
        assertThrows(IllegalArgumentException.class, ()-> {
                new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "voucherCode",
                        "SUCCESS", paymentData);});
    }

    @Test
    void testCreatePaymentBankTransfer(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "SofitaBank");
        paymentData.put("referenceCode", "123");

        Payment payment = new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7",
                orders.get(1), "Bank Transfer", "SUCCESS", paymentData);

        assertEquals("eb558e9f-1c39-460e-7760-33af6af63bd7", payment.getId());
        assertEquals(orders.get(1), payment.getOrder());
        assertEquals("Bank Transfer", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void  testCreatePaymentBankTransferWithEmptyBankName(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "123");

        assertThrows(IllegalArgumentException.class, ()-> {
                new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "Bank Transfer",
                        "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreatePaymentBankTransferWithEmptyReferenceCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "sofitaBank");
        paymentData.put("referenceCode", "");

        assertThrows(IllegalArgumentException.class, ()-> {
            new Payment("eb558e9f-1c39-460e-7760-33af6af63bd7", "Bank Transfer",
                    "SUCCESS", paymentData);
        });
    }


}