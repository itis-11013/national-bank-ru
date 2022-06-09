package ru.itis.nationalbankru.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

@Controller
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    public final OrganizationService organizationService;

    @PatchMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> updateOrganizationById(
            @PathVariable Long id,
            @RequestBody OrganizationRequestDto organizationRequestDto) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.updateOrganizationById(
                    id,
                    organizationRequestDto);
            return new GeneralResponse<OrganizationResponseDto>().successfulUpdateResponse(
                    organizationResponseDto,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<OrganizationResponseDto>().failureUpdateResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> getOrganizationById(@PathVariable Long id) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.getOrganizationById(id);
            return new GeneralResponse<OrganizationResponseDto>().successfulFetchResponse(
                    organizationResponseDto,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<OrganizationResponseDto>().failureFetchResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<Long>> deleteOrganizationById(@PathVariable Long id) {
        try {
            Long idResponse = organizationService.deleteOrganizationById(id);
            return new GeneralResponse<Long>().successfulDeleteResponse(
                    idResponse,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<Long>().failureDeleteResponse(
                    exception,
                    ResponseClass.organization);
        }
    }
}
