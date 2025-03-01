package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        // Buat objek Payment baru
        Payment payment = new Payment(order, method, paymentData);

        // Simpan ke repository
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String id) {
        // Ambil Payment berdasarkan ID, jika tidak ada return null
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayment(){
        return paymentRepository.getAllPayments();
    }

    @Override
    public void setStatus(Payment payment, String status){
        if (!payment.getStatus().equals(status)) {  // Hanya ubah jika status memang berubah
            payment.setStatus(status);
            paymentRepository.save(payment);  // Simpan hanya jika ada perubahan
        }
    }
}
