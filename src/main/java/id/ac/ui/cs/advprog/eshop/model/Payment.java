package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    // ====== Getter Methods ======
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    // ====== Constructor ======
    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
    }

    // ====== Validasi Pembayaran ======
    private void validatePayment() {
    }

    private void validateVoucher() {
    }

    private void validateCOD() {
    }

    private void validateBankTransfer() {
    }

    // ====== Setter Status ======
    public void setStatus(String status) {
    }

}
