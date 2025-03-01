package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Payment> payments;
    private List<Order> orders;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();

        Product product1 = new Product("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 2);
        Product product2 = new Product("a2c62328-4a37-4664-83c7-f32db8620155", "Sampo Cap Usep", 1);
        this.products.add(product1);
        this.products.add(product2);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat");
        Order order2 = new Order("12938476-011a-4c06-b534-44eb1396d69b", this.products, 1808680000L, "Anton Sutanto");

        orders.add(order1);
        orders.add(order2);
    }

    // ============ TEST VOUCHER PAYMENT =============

    @Test
    void testCreatePaymentVoucherFail8Numerical() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP0000000baba"); // Kurang 8 angka

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "VOUCHER", paymentData)
        );
    }

    @Test
    void testCreatePaymentVoucherFail16() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP0000000000"); // Kurang dari 16 karakter

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "VOUCHER", paymentData)
        );
    }

    @Test
    void testCreatePaymentVoucherFailESHOPStart() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHO000000000000"); // Tidak diawali "ESHOP"

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "VOUCHER", paymentData)
        );
    }

    @Test
    void testCreatePaymentSuccessVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678ABC"); // Valid

        Payment payment = new Payment("a3e3e3e3-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "VOUCHER", paymentData);

        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    // ============ TEST CASH ON DELIVERY PAYMENT =============

    @Test
    void testCreatePaymentFailCODAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", ""); // Address kosong
        paymentData.put("deliveryFee", "10000");

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("b3b3b3b3-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "COD", paymentData)
        );
    }

    @Test
    void testCreatePaymentFailCODDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Anggrek No. 1");
        paymentData.put("deliveryFee", ""); // Delivery fee kosong

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("c4c4c4c4-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "COD", paymentData)
        );
    }

    @Test
    void testCreatePaymentSuccessCOD() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Anggrek No. 1");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("d5d5d5d5-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "COD", paymentData);

        assertEquals("COD", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    // ============ TEST BANK TRANSFER PAYMENT =============

    @Test
    void testCreatePaymentFailBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", ""); // Bank kosong
        paymentData.put("referenceCode", "123456");

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("e6e6e6e6-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "BANK", paymentData)
        );
    }

    @Test
    void testCreatePaymentFailReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", ""); // Reference code kosong

        assertThrows(IllegalArgumentException.class, () ->
                new Payment("f7f7f7f7-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "BANK", paymentData)
        );
    }

    @Test
    void testCreatePaymentSuccessBank() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "123456");

        Payment payment = new Payment("g8g8g8g8-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "BANK", paymentData);

        assertEquals("BANK", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    // ============ TEST STATUS PAYMENT =============

    @Test
    void testSetPaymentStatusSuccess() {
        Payment payment = new Payment("h9h9h9h9-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "VOUCHER", new HashMap<>());
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", orders.get(1).getStatus());
    }

    @Test
    void testSetPaymentStatusRejected() {
        Payment payment = new Payment("i1i1i1i1-9a7f-4603-92c2-eaf529271cc9", orders.get(1), "VOUCHER", new HashMap<>());
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", orders.get(1).getStatus());
    }
}
