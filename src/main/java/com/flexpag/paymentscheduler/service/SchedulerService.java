package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.DTO.RegisterSchedulerDTO;
import com.flexpag.paymentscheduler.DTO.ResponseResgisterSchedulerDTO;
import com.flexpag.paymentscheduler.DTO.ResponseStatusSchedulerDTO;
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

    public ResponseResgisterSchedulerDTO save(RegisterSchedulerDTO dto){
        if (dto.getDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Por favor, insira uma data futura.");
        }
        var paymentScheduler = PaymentScheduler.builder()
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .expiredAt(dto.getDate())
                .build();
        var id = schedulerRepository.save(paymentScheduler).getId();
        return new ResponseResgisterSchedulerDTO(id);
    }

    public ResponseStatusSchedulerDTO searchStatusById(Long id){
        var paymentScheduler = searchById(id);
        return new ResponseStatusSchedulerDTO(paymentScheduler.getStatus(), paymentScheduler.getExpiredAt());
    }

    public void deleteById(Long id){
        var paymentScheduler = searchById(id);
        verifyPayment(paymentScheduler);
        schedulerRepository.deleteById(id);
    }

    public void updateSchedulerDate(Long id, RegisterSchedulerDTO newDate){
        var paymentScheduler = searchById(id);
        verifyPayment(paymentScheduler);
        paymentScheduler.setExpiredAt(newDate.getDate());
        schedulerRepository.save(paymentScheduler);
    }

    public void payScheduler(Long id){
        var paymentScheduler = searchById(id);
        verifyPayment(paymentScheduler);

        // Verifica se a data de agendamento está expirada
        if (LocalDateTime.now().isAfter(paymentScheduler.getExpiredAt())) {
            throw new RuntimeException("Não é possível pagar um agendamento expirado.");
        }
        paymentScheduler.setStatus(PaymentStatus.PAID);
        schedulerRepository.save(paymentScheduler);
    }

    private void verifyPayment(PaymentScheduler paymentScheduler){

        // Verifica se meu status de pagamento está pago
        if (paymentScheduler.getStatus().equals(PaymentStatus.PAID)) {
            throw new RuntimeException("Não é possível atualizar a data de agendamentos pagos.");
        }
    }

    private PaymentScheduler searchById(Long id){
        return schedulerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}