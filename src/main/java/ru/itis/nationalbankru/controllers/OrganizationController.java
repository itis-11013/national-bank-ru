package ru.itis.nationalbankru.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.enums.RequestStatus;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    public final OrganizationService organizationService;


    @GetMapping("/")
    public ResponseEntity<GeneralResponse<?>> getAllUserOrganizations(
            @RequestBody PageableDto pageableDto,
            RedirectAttributes redirectAttributes) {
        try {
            List<OrganizationResponseDto> organizationResponseDtos = organizationService.getAllUserOrganization(pageableDto);
            return ResponseEntity.ok(new GeneralResponse<List<OrganizationResponseDto>>().toBuilder()
                    .description("Successfully Fetched User Organizations")
                    .status(RequestStatus.SUCCESS)
                    .data(organizationResponseDtos)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Fetch User Organizations")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<?>> getOrganizationWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.getOrganizationWithId(id);
            return ResponseEntity.ok(new GeneralResponse<OrganizationResponseDto>().toBuilder()
                    .description("Successfully Fetched Organization")
                    .status(RequestStatus.SUCCESS)
                    .data(organizationResponseDto)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Fetch Organization")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<GeneralResponse<?>> createOrganization(
            @RequestBody OrganizationRequestDto organizationRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.createOrganization(organizationRequestDto);
            return ResponseEntity.ok(new GeneralResponse<OrganizationResponseDto>().toBuilder()
                    .description("Successfully Created Organization")
                    .status(RequestStatus.SUCCESS)
                    .data(organizationResponseDto)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Create Organization")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<?>> updateOrganizationWithId(
            @PathVariable UUID id,
            OrganizationRequestDto organizationRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.updateOrganizationWithId(
                    id,
                    organizationRequestDto);
            return ResponseEntity.ok(new GeneralResponse<OrganizationResponseDto>().toBuilder()
                    .description("Successfully Updated Organization")
                    .status(RequestStatus.SUCCESS)
                    .data(organizationResponseDto)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Update Organization")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<?>> deleteUserWithId(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            UUID uuid = organizationService.deleteOrganizationWithId(id);
            return ResponseEntity.ok(new GeneralResponse<UUID>().toBuilder()
                    .description("Successfully Deleted Organization")
                    .status(RequestStatus.SUCCESS)
                    .data(uuid)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Delete Organization")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }


}
