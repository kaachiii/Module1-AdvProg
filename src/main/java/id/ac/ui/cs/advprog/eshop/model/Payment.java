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
        this.id = (id != null) ? id : UUID.randomUUID().toString();
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = "PENDING";

        // Validasi sesuai metode pembayaran
        validatePayment();
    }

    // ====== Validasi Pembayaran ======
    private void validatePayment() {
        if (method.equals("VOUCHER")) {
            validateVoucher();
        } else if (method.equals("COD")) {
            validateCOD();
        } else if (method.equals("BANK")) {
            validateBankTransfer();
        } else {
            throw new IllegalArgumentException("Metode pembayaran tidak valid!");
        }
    }

    private void validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || !voucherCode.matches("^ESHOP\\d{8}[A-Z]{4}$")) {
            throw new IllegalArgumentException("Voucher code tidak valid!");
        }
        this.status = "SUCCESS"; // Jika valid, otomatis sukses
    }

    private void validateCOD() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            throw new IllegalArgumentException("Alamat atau biaya pengiriman tidak boleh kosong!");
        }
    }

    private void validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            throw new IllegalArgumentException("Nama bank dan kode referensi harus diisi!");
        }
    }

    // ====== Setter Status ======
    public void setStatus(String status) {
        if (!status.equals("SUCCESS") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Status tidak valid!");
        }
        this.status = status;

        // Ubah status Order berdasarkan status Payment
        if (status.equals("SUCCESS")) {
            this.order.setStatus("SUCCESS");
        } else if (status.equals("REJECTED")) {
            this.order.setStatus("FAILED");
        }
    }

}
