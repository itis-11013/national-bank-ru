package ru.itis.nationalbankru.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseDescription;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/organization")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

    public final OrganizationService organizationService;

    @GetMapping("/")
    public String getCreateOrganizationForm() {
        return "create_organization_page";
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> createOrganization(
            @Valid @RequestBody OrganizationRequestDto organizationRequestDto) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.createOrganization(organizationRequestDto);
            return new GeneralResponse<OrganizationResponseDto>().setSuccessResponse(
                    organizationResponseDto,
                    ResponseClass.organization);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> updateOrganizationById(
            @PathVariable Long id,
            @RequestBody OrganizationRequestDto organizationRequestDto) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.updateOrganizationById(
                    id,
                    organizationRequestDto);
            return new GeneralResponse<OrganizationResponseDto>().setSuccessResponse(
                    organizationResponseDto,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> getOrganizationById(@PathVariable Long id) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.getOrganizationById(id);
            return new GeneralResponse<OrganizationResponseDto>().setSuccessResponse(
                    organizationResponseDto,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<Long>> deleteOrganizationById(@PathVariable Long id) {
        try {
            Long idResponse = organizationService.deleteOrganizationById(id);
            return new GeneralResponse<Long>().setSuccessResponse(
                    idResponse,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<Long>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }
}
