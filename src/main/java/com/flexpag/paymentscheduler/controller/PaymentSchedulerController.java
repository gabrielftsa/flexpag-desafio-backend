package com.flexpag.paymentscheduler.controller;


import com.flexpag.paymentscheduler.DTO.RegisterSchedulerDTO;
import com.flexpag.paymentscheduler.DTO.ResponseResgisterSchedulerDTO;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}/status")
    public ResponseEntity<Object> searchStatusById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(schedulerService.searchStatusById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
