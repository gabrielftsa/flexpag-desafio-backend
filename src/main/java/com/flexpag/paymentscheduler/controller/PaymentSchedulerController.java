package com.flexpag.paymentscheduler.controller;


import com.flexpag.paymentscheduler.DTO.RegisterSchedulerDTO;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
public class PaymentSchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody RegisterSchedulerDTO registerSchedulerDTO){
        try {
            return ResponseEntity.ok(schedulerService.save(registerSchedulerDTO));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<Object> searchStatusById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(schedulerService.searchStatusById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        try {
            schedulerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody RegisterSchedulerDTO newDate, @PathVariable Long id){
        try {
            schedulerService.updateSchedulerDate(id, newDate);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}