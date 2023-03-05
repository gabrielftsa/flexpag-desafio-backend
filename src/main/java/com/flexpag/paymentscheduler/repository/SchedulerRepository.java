package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.entity.PaymentScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<PaymentScheduler, Long> {
}
