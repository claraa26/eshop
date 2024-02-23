package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Cabai Merah");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        Product result = productService.create(product);

        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository).create(product);
    }

    @Test
    void testFindAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());

        productService.findAll();

        verify(productRepository).findAll();
    }

    @Test
    void testEditProduct() {
        Product editedProduct = new Product();
        when(productRepository.edit(editedProduct)).thenReturn(editedProduct);

        Product result = productService.edit(editedProduct);

        assertEquals(editedProduct, result);
        verify(productRepository).edit(editedProduct);
    }

    @Test
    void testFindByProductId() {
        String productId = "123";
        Product product = new Product();
        when(productRepository.findByProductId(productId)).thenReturn(product);

        Product result = productService.findByProductId(productId);

        assertEquals(product, result);
        verify(productRepository).findByProductId(productId);
    }

    @Test
    void testDeleteProduct() {
        String productId = "456";

        productService.delete(productId);

        verify(productRepository).delete(productId);
    }
}
