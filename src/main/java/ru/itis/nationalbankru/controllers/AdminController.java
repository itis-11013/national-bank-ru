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
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
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
            return new GeneralResponse<List<OrganizationResponseDto>>().setSuccessResponse(
                    organizationResponseDtos,
                    ResponseDescription.fetched,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<List<OrganizationResponseDto>>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.fetch,
                    ResponseClass.organization);
        }
    }

    @PatchMapping("/organization/{id}")
    public ResponseEntity<GeneralResponse<UUID>> banOrganizationWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            organizationService.banOrganizationWithId(id);
            return new GeneralResponse<UUID>().setSuccessResponse(
                    id,
                    ResponseDescription.banned,
                    ResponseClass.organization);
        } catch (Exception exception) {
            return new GeneralResponse<UUID>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.ban,
                    ResponseClass.organization);
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<GeneralResponse<UUID>> banUserWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            userService.banUserWithId(id);
            return new GeneralResponse<UUID>().setSuccessResponse(
                    id,
                    ResponseDescription.banned,
                    ResponseClass.user);
        } catch (Exception exception) {
            return new GeneralResponse<UUID>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.fetch,
                    ResponseClass.organization);
        }
    }
}
