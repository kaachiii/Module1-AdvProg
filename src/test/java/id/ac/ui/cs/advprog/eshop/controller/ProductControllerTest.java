package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("327bf290-f721-4b59-8c64-a212f7e911ac");
        sampleProduct.setProductName("Sample Product");
        sampleProduct.setProductQuantity(3);
    }

    @Test
    void testCreateProductPage() {
        String view = productController.createProductPage(model);
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", view);
    }

    @Test
    void testCreateProductPost() {
        String view = productController.createProductPost(sampleProduct, model);
        verify(productService).create(sampleProduct);
        assertEquals("redirect:list", view);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(sampleProduct);
        when(productService.findAll()).thenReturn(productList);

        String view = productController.productListPage(model);

        verify(productService).findAll();
        verify(model).addAttribute("products", productList);
        assertEquals("productList", view);
    }

    @Test
    void testEditProductPage() {
        when(productService.findProductByID("327bf290-f721-4b59-8c64-a212f7e911ac")).thenReturn(sampleProduct);

        String view = productController.editProductPage(model, "327bf290-f721-4b59-8c64-a212f7e911ac");

        verify(productService).findProductByID("327bf290-f721-4b59-8c64-a212f7e911ac");
        verify(model).addAttribute("product", sampleProduct);
        assertEquals("editProduct", view);
    }

    @Test
    void testEditProductPost() {
        String view = productController.editProductPost(sampleProduct, model, "327bf290-f721-4b59-8c64-a212f7e911ac");

        verify(productService).edit(sampleProduct);
        assertEquals("327bf290-f721-4b59-8c64-a212f7e911ac", sampleProduct.getProductId());
        assertEquals("redirect:../list", view);
    }

    @Test
    void testDeleteProductPage() {
        String view = productController.deleteProductPage(model, "327bf290-f721-4b59-8c64-a212f7e911ac");

        verify(productService).deleteProductByID("327bf290-f721-4b59-8c64-a212f7e911ac");
        assertEquals("redirect:/product/list", view);
    }
}
