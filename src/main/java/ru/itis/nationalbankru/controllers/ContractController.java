package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
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
            @RequestBody ContractRequestDto contractRequestDto) {
        try {
            ContractResponseDto contractResponseDto = contractService.createContract(contractRequestDto);
            return new GeneralResponse<ContractResponseDto>().setSuccessResponse(
                    contractResponseDto,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<ContractResponseDto>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<ContractResponseDto>> getContractById(@PathVariable UUID id) {
        try {
            ContractResponseDto contractResponseDto = contractService.getContractById(id);
            return new GeneralResponse<ContractResponseDto>().setSuccessResponse(
                    contractResponseDto,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<ContractResponseDto>().setFailureResponse(
                    exception,
                    ResponseClass.contract);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<UUID>> deleteContractById(@PathVariable UUID id) {
        try {
            UUID uuid = contractService.deleteContractById(id);
            return new GeneralResponse<UUID>().setSuccessResponse(
                    uuid,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<UUID>().setFailureResponse(
                    exception,
                    ResponseClass.contract);

        }
    }
}
