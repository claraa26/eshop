package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductControllerTest{

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    ProductController productController;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String result = productController.createProductPage(model);
        assertEquals("createProduct", result);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String result = productController.createProductPost(product, model);
        assertEquals("redirect:list", result);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product product = new Product();
        String result = productController.editProductPage(productId, model);

        assertEquals("editProduct", result);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String result = productController.editProductPost(product, model);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testDeleteProduct() {
        String productId = "456";
        String result = productController.deleteProduct(productId);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testProductListPage() {
        String result = productController.productListPage(model);
        assertEquals("productList", result);

    }
}
