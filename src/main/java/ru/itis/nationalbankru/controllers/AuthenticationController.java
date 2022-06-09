package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.dto.validators.OnCreate;
import ru.itis.nationalbankru.services.organization.OrganizationService;

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

    @PostMapping("/signUp")
    public ResponseEntity<GeneralResponse<Long>> signUp(
            @Validated(OnCreate.class) @RequestBody OrganizationRequestDto organizationRequestDto) {
        try {
            Long id = organizationService.createOrganization(organizationRequestDto).getId();
            return new GeneralResponse<Long>().successfulCreateResponse(id, GeneralResponse.ResponseClass.organization);
        } catch (Exception e) {
            return new GeneralResponse<Long>().failureCreateResponse(e, GeneralResponse.ResponseClass.organization);
        }
    }

}
