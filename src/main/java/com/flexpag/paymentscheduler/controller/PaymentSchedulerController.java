package com.flexpag.paymentscheduler.controller;


import com.flexpag.paymentscheduler.DTO.RegisterSchedulerDTO;
import com.flexpag.paymentscheduler.DTO.ResponseResgisterSchedulerDTO;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
public class PaymentSchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseResgisterSchedulerDTO save(@RequestBody RegisterSchedulerDTO registerSchedulerDTO){
        return schedulerService.save(registerSchedulerDTO);
    }

}
