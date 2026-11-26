package com.project.paymentserviceRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.paymentserviceEntity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
