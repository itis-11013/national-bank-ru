package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrganizationService organizationService;

    @GetMapping("/organization/")
    public ResponseEntity<GeneralResponse<List<OrganizationResponseDto>>> getAllOrganizations(
            @RequestBody PageableDto pageableDto) {
        try {
            List<OrganizationResponseDto> organizationResponseDtos = organizationService.getAllOrganization(pageableDto);
            return new GeneralResponse<List<OrganizationResponseDto>>().setSuccessResponse(
                    organizationResponseDtos,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<List<OrganizationResponseDto>>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }

    @PatchMapping("/organization/{id}")
    public ResponseEntity<GeneralResponse<Long>> banOrganizationWithId(@PathVariable Long id) {
        try {
            organizationService.banOrganizationById(id);
            return new GeneralResponse<Long>().setSuccessResponse(
                    id,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<Long>().setFailureResponse(
                    exception,
                    ResponseClass.organization);
        }
    }
}
