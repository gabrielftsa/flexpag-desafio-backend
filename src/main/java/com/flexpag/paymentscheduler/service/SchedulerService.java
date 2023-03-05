package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.DTO.RegisterSchedulerDTO;
import com.flexpag.paymentscheduler.DTO.ResponseResgisterSchedulerDTO;
import com.flexpag.paymentscheduler.entity.PaymentScheduler;
import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchedulerService {

    @Autowired
    private SchedulerRepository schedulerRepository;

    public ResponseResgisterSchedulerDTO save(RegisterSchedulerDTO days){
        var paymentScheduler = new PaymentScheduler();
        paymentScheduler.setStatus(PaymentStatus.PENDING);
        paymentScheduler.setCreatedAt(LocalDateTime.now());
        paymentScheduler.setExpiredAt(paymentScheduler.getCreatedAt().plusDays(days.getDays()));
        var id = schedulerRepository.save(paymentScheduler).getId();
        return new ResponseResgisterSchedulerDTO(id);
    }
}
