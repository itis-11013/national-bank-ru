package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
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
}
