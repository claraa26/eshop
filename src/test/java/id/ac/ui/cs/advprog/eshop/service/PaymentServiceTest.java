package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest{
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List <Order> orders;
    List<Product> products;

    @BeforeEach
    void setUp(){
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Cabai Merah");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("acp58g99-1c39-450h-8860-71af6af63cd7",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();
        Map<String, String> paymentDataWithVoucher = new HashMap<>();
        Map<String, String> paymentDataWithBankTransfer = new HashMap<>();

        paymentDataWithVoucher.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "Voucher Code",
                "SUCCESS", paymentDataWithVoucher);

        paymentDataWithBankTransfer.put("bankName", "sofitaBank");
        paymentDataWithBankTransfer.put("referenceCode", "123");
        Payment payment2 = new Payment("eb558e9f-1c39-460e-7760-a9af6af63bd6", "Bank Transfer",
                "SUCCESS", paymentDataWithBankTransfer);

        payments.add(payment1);
        payments.add(payment2);
    }
    @Test
    void testCreatePayment(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.createPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testCreatePaymentIfAlreadyExists(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertNull(paymentService.createPayment(payment));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testUpdateStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                PaymentStatus.SUCCESS.getValue(), payment.getPaymentData());
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.updateStatus(payment.getId(),
                PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidStatus(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.updateStatus(payment.getId(), "MEOW");
        });
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateStatusInvalidPaymentId(){
        doReturn(null).when(paymentRepository).findById("zczc");

        assertThrows(NoSuchElementException.class,
                () -> paymentService.updateStatus("zczc", PaymentStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }
    @Test
    void testFindByIdIfIdFound(){
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }
    @Test
    void testFindByIdIfIdNotFound(){
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.findById("zczc"));
    }
}