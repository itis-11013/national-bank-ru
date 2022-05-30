package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.enums.RequestStatus;
import ru.itis.nationalbankru.services.organization.OrganizationService;
import ru.itis.nationalbankru.services.user.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final OrganizationService organizationService;

    @GetMapping("/organization")
    public ResponseEntity<GeneralResponse<List<OrganizationResponseDto>>> getAllOrganizations(
            @RequestBody PageableDto pageableDto,
            RedirectAttributes redirectAttributes) {
        try {
            List<OrganizationResponseDto> organizationResponseDtos = organizationService.getAllOrganization(pageableDto);
            return ResponseEntity.ok(new GeneralResponse<List<OrganizationResponseDto>>().toBuilder()
                    .description("Successfully Fetched Organizations")
                    .status(RequestStatus.SUCCESS)
                    .data(organizationResponseDtos)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<List<OrganizationResponseDto>>().toBuilder()
                    .description("Failed To Fetch Organizations")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @PatchMapping("/organization/{id}")
    public ResponseEntity<GeneralResponse<?>> banOrganizationWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            organizationService.banOrganizationWithId(id);
            return ResponseEntity.ok(new GeneralResponse<String>().toBuilder()
                    .description("Successfully Banned Organization")
                    .status(RequestStatus.SUCCESS)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Ban Organization")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<GeneralResponse<?>> banUserWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            userService.banUserWithId(id);
            return ResponseEntity.ok(new GeneralResponse<UUID>().toBuilder()
                    .description("Successfully Banned User")
                    .status(RequestStatus.SUCCESS)
                    .data(id)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Ban User")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }
}
