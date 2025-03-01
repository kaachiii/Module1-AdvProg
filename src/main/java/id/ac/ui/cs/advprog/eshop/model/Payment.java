package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        this.status = PaymentStatus.WAITING_PAYMENT.getValue();

        // Validasi sesuai metode pembayaran
        validatePayment();
    }

    // ====== Validasi Pembayaran ======
    private void validatePayment() {
        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            validateVoucher();
            return;
        } else if (method.equals(PaymentMethod.COD.getValue())) {
            validateCOD();
            return;
        } else if (method.equals(PaymentMethod.BANK.getValue())) {
            validateBankTransfer();
            return;
        }
        this.status = PaymentStatus.REJECTED.getValue();
        this.order.setStatus(OrderStatus.FAILED.getValue());
    }

    private void validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || !validateVoc(voucherCode)) {
            this.status = PaymentStatus.REJECTED.getValue();
            this.order.setStatus(OrderStatus.FAILED.getValue());
            return;
        }
        this.status = PaymentStatus.SUCCESS.getValue(); // Jika valid, otomatis sukses
        this.order.setStatus(OrderStatus.SUCCESS.getValue());
    }

    private boolean validateVoc(String voucher){
        if ((!voucher.startsWith("ESHOP")) || voucher.length() != 16) {
            return false;
        }
        int numCount = 0;
        int alphaCount = 0;
        for (int i = 5; i < voucher.length(); i++) {
            char ch = voucher.charAt(i);
            if (ch >= '0' && ch <= '9') {
                numCount++;
            }
            if (ch >= 'A' && ch <= 'Z') {
                alphaCount++;
            }
        }
        if (numCount == 8 && alphaCount == 3) {
            return true;
        }
        return false;
    }

    private void validateCOD() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address == null || address.isEmpty() || deliveryFee == null || deliveryFee.isEmpty()) {
            this.status = PaymentStatus.REJECTED.getValue();
            this.order.setStatus(OrderStatus.FAILED.getValue());
        }
        else {
            this.status = PaymentStatus.SUCCESS.getValue();
            this.order.setStatus(OrderStatus.SUCCESS.getValue()); // Order sukses jika payment sukses
        }
    }

    private void validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            this.status = PaymentStatus.REJECTED.getValue();
            this.order.setStatus(OrderStatus.FAILED.getValue());
        }
        else {
            this.status = PaymentStatus.SUCCESS.getValue();
            this.order.setStatus(OrderStatus.SUCCESS.getValue()); // Order sukses jika payment sukses
        }
    }

    // ====== Setter Status ======
    public void setStatus(String status) {
        if (!status.equals(PaymentStatus.SUCCESS.getValue()) && !status.equals(PaymentStatus.REJECTED.getValue())) {
            this.status = PaymentStatus.REJECTED.getValue();
            this.order.setStatus(OrderStatus.FAILED.getValue());
            return;
        }
        this.status = status;

        // Ubah status Order berdasarkan status Payment
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            this.order.setStatus(OrderStatus.SUCCESS.getValue());
        } else {
            this.order.setStatus(OrderStatus.FAILED.getValue());
        }
    }

}
