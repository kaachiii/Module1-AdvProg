package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("327bf290-f721-4b59-8c64-a212f7e911ac");
        sampleProduct.setProductName("Sample Product");
        sampleProduct.setProductQuantity(5);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(sampleProduct)).thenReturn(sampleProduct);

        Product result = productService.create(sampleProduct);

        verify(productRepository).create(sampleProduct);
        assertEquals(sampleProduct, result);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = Arrays.asList(sampleProduct);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        verify(productRepository).findAll();
        assertEquals(1, result.size());
        assertEquals(sampleProduct, result.get(0));
    }

    @Test
    void testFindProductByID() {
        when(productRepository.findProductByID("327bf290-f721-4b59-8c64-a212f7e911ac")).thenReturn(sampleProduct);

        Product result = productService.findProductByID("327bf290-f721-4b59-8c64-a212f7e911ac");

        verify(productRepository).findProductByID("327bf290-f721-4b59-8c64-a212f7e911ac");
        assertEquals(sampleProduct, result);
    }

    @Test
    void testEditProduct() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setProductName("Produk Lama");
        existingProduct.setProductQuantity(10);
        existingProduct.setProductId("6afb1a74-fe3e-4559-85d1-c409ff43baac");

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Produk Baru");
        updatedProduct.setProductQuantity(20);
        updatedProduct.setProductId("6afb1a74-fe3e-4559-85d1-c409ff43baac");

        when(productRepository.edit(updatedProduct)).thenReturn(updatedProduct);

        productService.edit(updatedProduct);

        verify(productRepository, times(1)).edit(updatedProduct);
    }

    @Test
    void testDeleteProductByID() {
        doNothing().when(productRepository).deleteProductById("327bf290-f721-4b59-8c64-a212f7e911ac");

        productService.deleteProductByID("327bf290-f721-4b59-8c64-a212f7e911ac");

        verify(productRepository).deleteProductById("327bf290-f721-4b59-8c64-a212f7e911ac");
    }
}

