package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.organization.OrganizationRequestDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final OrganizationService organizationService;

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in_page";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(
            @Valid @RequestBody OrganizationRequestDto organizationRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            organizationService.createOrganization(organizationRequestDto);
            return "redirect:/auth/signIn";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getStackTrace());
            return "redirect:/auth/signUp";
        }
    }

}
