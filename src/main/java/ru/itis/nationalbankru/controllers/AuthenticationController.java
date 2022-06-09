package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final OrganizationService organizationService;

    @GetMapping("/signIn")
    public String getSignInPage() {
        OrganizationResponseDto organization = organizationService.getCurrentUser();
        if (organization != null) {
            return "redirect:/";
        }
        return "sign_in_page";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        OrganizationResponseDto organization = organizationService.getCurrentUser();
        if (organization != null) {
            return "redirect:/";
        }
        return "sign_up_page";
    }

    @PostMapping("/singUp")
    public ResponseEntity<GeneralResponse<OrganizationResponseDto>> createOrganization(
            @Valid @RequestBody OrganizationRequestDto organizationRequestDto) {
        try {
            OrganizationResponseDto organizationResponseDto = organizationService.createOrganization(organizationRequestDto);
            return new GeneralResponse<OrganizationResponseDto>().successfulCreateResponse(
                    organizationResponseDto,
                    GeneralResponse.ResponseClass.organization);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new GeneralResponse<OrganizationResponseDto>().failureCreateResponse(
                    exception,
                    GeneralResponse.ResponseClass.organization);
        }
    }
}
