package com.flexpag.paymentscheduler.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterSchedulerDTO {
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
}
