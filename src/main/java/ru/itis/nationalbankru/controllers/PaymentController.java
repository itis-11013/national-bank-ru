package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.services.payment.PaymentService;

import java.util.UUID;

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

    @PostMapping("/{id}")
    public ResponseEntity<GeneralResponse<ContractResponseDto>> createPayment(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            ContractResponseDto contractResponseDto = paymentService.submitPayment(id);
            return new GeneralResponse<ContractResponseDto>().setSuccessResponse(
                    contractResponseDto,
                    GeneralResponse.ResponseDescription.payed,
                    GeneralResponse.ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<ContractResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    GeneralResponse.ResponseDescription.pay,
                    GeneralResponse.ResponseClass.contract);
        }
    }
}
