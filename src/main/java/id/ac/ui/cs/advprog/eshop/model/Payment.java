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
        this(order, method, paymentData);
        this.id = id;
    }

    public Payment(Order order, String method, Map<String, String>paymentData){
        this.id = UUID.randomUUID().toString();
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = "WAITING_PAYMENT";

        // Validasi sesuai metode pembayaran
        validatePayment();
    }

    // ====== Validasi Pembayaran ======
    private void validatePayment() {
        if (method.equals("VOUCHER")) {
            validateVoucher();
            return;
        } else if (method.equals("COD")) {
            validateCOD();
            return;
        } else if (method.equals("BANK")) {
            validateBankTransfer();
            return;
        }
        this.status = "REJECTED";
        this.order.setStatus("FAILED");
    }

    private void validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || !voucherCode.matches("^ESHOP(?=(?:[^0-9]*\\d[^0-9]*){8}$)[A-Z]{11}$")) {
            this.status = "REJECTED";
            this.order.setStatus("FAILED");
            return;
        }
        this.status = "SUCCESS"; // Jika valid, otomatis sukses
        this.order.setStatus("SUCCESS");
    }

    private void validateCOD() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            this.status = "REJECTED";
            this.order.setStatus("FAILED");
        }
        else {
            this.status = "SUCCESS";
            this.order.setStatus("SUCCESS"); // Order sukses jika payment sukses
        }
    }

    private void validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            this.status = "REJECTED";
            this.order.setStatus("FAILED");
        }
        else {
            this.status = "SUCCESS";
            this.order.setStatus("SUCCESS"); // Order sukses jika payment sukses
        }
    }

    // ====== Setter Status ======
    public void setStatus(String status) {
        if (!status.equals("SUCCESS") && !status.equals("REJECTED")) {
            this.status = "REJECTED";
            this.order.setStatus("FAILED");
            return;
        }
        this.status = status;

        // Ubah status Order berdasarkan status Payment
        if (status.equals("SUCCESS")) {
            this.order.setStatus("SUCCESS");
        } else {
            this.order.setStatus("FAILED");
        }
    }

}
