package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest{

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Cabai Merah");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("a9hjk189-00ab-jkiq-jie6-095y-5o43g3pwfbq6");
        product1.setProductName("Bawang Merah");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("07837423-095b-4451-a922-7049838bcbf6");
        product2.setProductName("Bawang Putih");
        product2.setProductQuantity(70);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product originalProduct = new Product();
        originalProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        originalProduct.setProductName("Cabai Merah");
        originalProduct.setProductQuantity(100);
        productRepository.create(originalProduct);

        // Update the product details
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Cabai Hijau");
        updatedProduct.setProductQuantity(150);
        productRepository.edit(updatedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(originalProduct.getProductName(), savedProduct.getProductName());
        assertEquals(originalProduct.getProductId(), savedProduct.getProductId());
        assertEquals(originalProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testDeleteProduct() {
        // Create a product and save it
        Product productToDelete = new Product();
        productToDelete.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productToDelete.setProductName("Cabai Merah");
        productToDelete.setProductQuantity(100);
        productRepository.create(productToDelete);

        // Delete the product from the repository
        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Try to retrieve the deleted product
        Product deletedProduct = productRepository.findByProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Assert that the product is not found (deleted)
        assertNull(deletedProduct);
    }
}