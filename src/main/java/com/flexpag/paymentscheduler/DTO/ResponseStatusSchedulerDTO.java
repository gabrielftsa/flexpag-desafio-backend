package com.flexpag.paymentscheduler.DTO;

import com.flexpag.paymentscheduler.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatusSchedulerDTO {

    private PaymentStatus status;
}
