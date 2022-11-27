package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.contract.ContractResponseDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.dto.product.ProductResponseDto;
import ru.itis.nationalbankru.services.contract.ContractService;
import ru.itis.nationalbankru.services.organization.OrganizationService;
import ru.itis.nationalbankru.services.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Secured("ADMIN")
public class AdminController {

    private final OrganizationService organizationService;
    private final ContractService contractService;
    private final ProductService productService;

    @GetMapping("/organization/")
    public ResponseEntity<GeneralResponse<List<OrganizationResponseDto>>> getAllOrganizations(
            @RequestBody PageableDto pageableDto) {
        try {
            List<OrganizationResponseDto> organizationResponseDtos = organizationService.getAllOrganization(pageableDto);
            return new GeneralResponse<List<OrganizationResponseDto>>().successfulFetchResponse(
                    organizationResponseDtos,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<List<OrganizationResponseDto>>().failureFetchResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/contract/")
    public ResponseEntity<GeneralResponse<List<ContractResponseDto>>> getAllContracts(
            @RequestBody PageableDto pageableDto) {
        try {
            List<ContractResponseDto> contractResponseDtos = contractService.getAllContracts(pageableDto);
            return new GeneralResponse<List<ContractResponseDto>>().successfulFetchResponse(
                    contractResponseDtos,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<List<ContractResponseDto>>().failureFetchResponse(
                    exception,
                    ResponseClass.contract);
        }
    }

    @GetMapping("/product/")
    public ResponseEntity<GeneralResponse<List<ProductResponseDto>>> getAllProducts(
            @RequestBody PageableDto pageableDto) {
        try {
            List<ProductResponseDto> contractResponseDtos = productService.getAllProducts(pageableDto);
            return new GeneralResponse<List<ProductResponseDto>>().successfulFetchResponse(
                    contractResponseDtos,
                    ResponseClass.contract);
        } catch (Exception exception) {
            return new GeneralResponse<List<ProductResponseDto>>().failureFetchResponse(
                    exception,
                    ResponseClass.contract);
        }
    }

    @PatchMapping("/organization/{id}")
    public ResponseEntity<GeneralResponse<Long>> banOrganizationWithId(@PathVariable Long id) {
        try {
            organizationService.banOrganizationById(id);
            return new GeneralResponse<Long>().successfulBanResponse(
                    id,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<Long>().failureBanResponse(
                    exception,
                    ResponseClass.organization);
        }
    }
}
