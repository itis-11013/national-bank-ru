package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseDescription;
import ru.itis.nationalbankru.dto.contract.ContractRequestDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.services.contract.ContractService;

import java.util.UUID;

/**
 * @author : Escalopa
 * @created : 30.05.2022, Mon
 * @time : 08:57
 **/

@Controller
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping("/")
    public ResponseEntity<GeneralResponse<ContractResponseDto>> createContract(
            @RequestBody ContractRequestDto contractRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            ContractResponseDto contractResponseDto = contractService.createContract(contractRequestDto);
            return new GeneralResponse<ContractResponseDto>().setSuccessResponse(
                    contractResponseDto,
                    ResponseDescription.created,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<ContractResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.fetch,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<ContractResponseDto>> getContractById(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            ContractResponseDto contractResponseDto = contractService.getContractById(id);
            return new GeneralResponse<ContractResponseDto>().setSuccessResponse(
                    contractResponseDto,
                    ResponseDescription.fetch,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<ContractResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.fetch,
                    ResponseClass.organization);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<UUID>> deleteContractById(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            UUID uuid = contractService.deleteContractById(id);
            return new GeneralResponse<UUID>().setSuccessResponse(
                    uuid,
                    ResponseDescription.fetch,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<UUID>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.fetch,
                    ResponseClass.organization);

        }
    }
}
