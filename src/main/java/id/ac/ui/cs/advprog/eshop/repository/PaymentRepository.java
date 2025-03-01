package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment){
        for (Payment paymentData: paymentData){
            if (paymentData.getId().equals(payment.getId())){
                throw new IllegalStateException();
            }
        }
        paymentData.add(payment);

        // Perbarui status order sesuai status payment
        if (payment.getStatus().equals("SUCCESS")) {
            payment.getOrder().setStatus("SUCCESS");
        } else if (payment.getStatus().equals("REJECTED")) {
            payment.getOrder().setStatus("FAILED");
        }

        return payment;
    }
    public Payment findById(String id){
        for (Payment payment : paymentData){
            if (payment.getId().equals(id)){
                return payment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments(){
        return new ArrayList<>(paymentData);
    }
}
