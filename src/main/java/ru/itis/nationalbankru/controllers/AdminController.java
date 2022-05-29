package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.PageableDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.entity.enums.RequestStatus;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final OrganizationService organizationService;

    @GetMapping("/org")
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
}
