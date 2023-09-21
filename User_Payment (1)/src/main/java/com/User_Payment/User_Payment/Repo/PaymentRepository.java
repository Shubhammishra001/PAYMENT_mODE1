package com.User_Payment.User_Payment.Repo;

import com.User_Payment.User_Payment.Entity.Payment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
