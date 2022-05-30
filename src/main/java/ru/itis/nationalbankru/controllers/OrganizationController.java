package ru.itis.nationalbankru.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseDescription;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    public final OrganizationService organizationService;

    @GetMapping("/")
    public ResponseEntity<GeneralResponse<List<OrganizationResponseDto>>> getAllOrganizations(
            @RequestBody PageableDto pageableDto,
            RedirectAttributes redirectAttributes) {
        try {
            List<OrganizationResponseDto> organizationResponseDtos = organizationService.getAllUserOrganization(pageableDto);
            return new GeneralResponse<List<OrganizationResponseDto>>().setSuccessResponse(
                    organizationResponseDtos,
                    ResponseDescription.deleted,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<List<OrganizationResponseDto>>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> getOrganizationWithId(
            @PathVariable UUID id,
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

    @PostMapping("/new")
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
            return new GeneralResponse<OrganizationResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.organization);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> updateOrganizationWithId(
            @PathVariable UUID id,
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
    public ResponseEntity<GeneralResponse<UUID>> deleteUserWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            UUID uuid = organizationService.deleteOrganizationWithId(id);
            return new GeneralResponse<UUID>().setSuccessResponse(
                    uuid,
                    ResponseDescription.deleted,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<UUID>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.organization);
        }
    }
}
