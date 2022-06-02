package ru.itis.nationalbankru.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseDescription;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

@Controller
@RequestMapping("/organization")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {

    public final OrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> getOrganizationWithId(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.getOrganizationWithId(id);
            return new GeneralResponse<OrganizationResponseDto>().setSuccessResponse(
                    organizationResponseDto,
                    ResponseDescription.deleted,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/")
    public String getCreateOrganizationForm() {
        return "create_organization_page";
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> createOrganization(
            @RequestBody OrganizationRequestDto organizationRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.createOrganization(organizationRequestDto);
            return new GeneralResponse<OrganizationResponseDto>().setSuccessResponse(
                    organizationResponseDto,
                    ResponseDescription.deleted,
                    ResponseClass.organization);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.organization);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> updateOrganizationWithId(
            @PathVariable Long id,
            OrganizationRequestDto organizationRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.updateOrganizationWithId(
                    id,
                    organizationRequestDto);
            return new GeneralResponse<OrganizationResponseDto>().setSuccessResponse(
                    organizationResponseDto,
                    ResponseDescription.updated,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.update,
                    ResponseClass.organization);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<Long>> deleteUserWithId(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            Long idResponse = organizationService.deleteOrganizationWithId(id);
            return new GeneralResponse<Long>().setSuccessResponse(
                    idResponse,
                    ResponseDescription.deleted,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<Long>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.organization);
        }
    }
}
