package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
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
    void testCreateAndFindWithInvalidProductName() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals("Nama Produk Tidak Valid!", savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditProductWithInvalidInput() {
        Product product = new Product();
        product.setProductId("da1900fd-96c7-41a7-9dcf-584982000257");
        product.setProductName("Ayam Geprek");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("");
        editedProduct.setProductQuantity(-1);
        Product updatedProduct = productRepository.edit(editedProduct);

        assertNotNull(updatedProduct);
        assertEquals("Nama Produk Tidak Valid!", updatedProduct.getProductName());
        assertEquals(0, updatedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
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
    void testCreateAndFindWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(-99);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(0, savedProduct.getProductQuantity());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("da1900fd-96c7-41a7-9dcf-584982000257");
        product.setProductName("Ayam Geprek");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Jus Mangga");
        editedProduct.setProductQuantity(50);
        Product updatedProduct = productRepository.edit(editedProduct);

        assertNotNull(updatedProduct);
        assertEquals("Jus Mangga", updatedProduct.getProductName());
        assertEquals(50, updatedProduct.getProductQuantity());
    }

    @Test
    void testEditProductWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId("4f9ae221-cf75-40df-8520-c50f354f7cc3");
        product.setProductName("Nasi Padang");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Es Cokelat");
        editedProduct.setProductQuantity(-10);
        Product updatedProduct = productRepository.edit(editedProduct);

        assertNotNull(updatedProduct);
        assertEquals("Es Cokelat", updatedProduct.getProductName());
        assertEquals(0, updatedProduct.getProductQuantity()); // Quantity tidak boleh negatif
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("da1900fd-96c7-41a7-9dcf-584982000257");
        product.setProductName("Ayam Geprek");
        product.setProductQuantity(20);
        productRepository.create(product);

        productRepository.delete(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext()); // Produk harus sudah terhapus
    }

    @Test
    void testDeleteProductById() {
        Product product = new Product();
        product.setProductId("4f9ae221-cf75-40df-8520-c50f354f7cc3");
        product.setProductName("Nasi Padang");
        product.setProductQuantity(30);
        productRepository.create(product);

        productRepository.deleteProductById("4f9ae221-cf75-40df-8520-c50f354f7cc3");

        Product foundProduct = productRepository.findProductByID("4f9ae221-cf75-40df-8520-c50f354f7cc3");
        assertNull(foundProduct); // Produk harus sudah terhapus
    }

    @Test
    void testDeleteNonExistentProduct() {
        assertDoesNotThrow(() -> productRepository.deleteProductById("a0cb40a3-2077-4844-9961-8cd14b61c27f")); // Tidak boleh error
    }

    @Test
    void testEditAndDelete() {
        Product product = new Product();
        product.setProductId("1cb4b872-6bd2-4294-9455-cb89426ca591");
        product.setProductName("ABCD Uncle Muthu");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Nasi Lemak Opah");
        editedProduct.setProductQuantity(99999);
        Product updatedProduct = productRepository.edit(editedProduct);

        assertNotNull(updatedProduct);
        productRepository.delete(updatedProduct);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindProductById() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Nasi Duduk");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Cakar Ayam");
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product findProduct1 = productRepository.findProductByID(product1.getProductId());
        Product findProduct2 = productRepository.findProductByID(product2.getProductId());
        assertNotNull(findProduct1);
        assertNotNull(findProduct2);
        assertEquals("Nasi Duduk", findProduct1.getProductName());
        assertEquals("Cakar Ayam", findProduct2.getProductName());
    }

    @Test
    void testFindNotExistentProductById() {
        Product findProduct1 = productRepository.findProductByID("fffffff");
        assertNull(findProduct1);
    }
}
