package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest{
    private List<Product> products;

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
    }

    @Test
    void testCreateOrderEmptyProduct(){
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                    this.products, 1708560000L, "Safira Sudrajat");
        });
    }

    @Test
    void testCreateOrderDefaultStatus(){
        Order order = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                this.products, 1708560000L, "Safira Sudrajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Cabai Merah", order.getProducts().get(0).getProductName());
        assertEquals("Cabai Hijau", order.getProducts().get(1).getProductName());

        assertEquals("acp58g99-1c39-450h-8860-71af6af63bd6", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safaira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus(){
        Order order = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                this.products, 1708560000L, "Safira Sudrajat", "SUCCESS");
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus(){
        assertThrows(IllegalArgumentException.class, () ->{
            Order order = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                    this.products, 1708560000L, "Safira Sudrajat", "MEOW");
        });
    }

    @Test
    void testSetStatusToCancelled(){
        Order order = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus(){
        Order order = new Order("acp58g99-1c39-450h-8860-71af6af63bd6",
                this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));

    }
}