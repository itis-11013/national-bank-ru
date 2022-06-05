package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.dto.payment.PaymentRequestDto;
import ru.itis.nationalbankru.services.payment.PaymentService;

import javax.validation.Valid;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 21:54
 **/

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<GeneralResponse<ContractResponseDto>> createPayment(
            @Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        try {
            ContractResponseDto contractResponseDto = paymentService.submitPayment(paymentRequestDto.getContractId());
            return new GeneralResponse<ContractResponseDto>().successfulCreateResponse(
                    contractResponseDto,
                    GeneralResponse.ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<ContractResponseDto>().failureCreateResponse(
                    exception,
                    GeneralResponse.ResponseClass.contract);
        }
    }
}
