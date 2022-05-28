package ru.itis.nationalbankru.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.services.user.UserServiceImpl;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    private final UserServiceImpl userService;

    public AuthenticationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in_page";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(UserRequestDto userRequestDto, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(userRequestDto);
            return "redirect:/auth/signIn";
        } catch (Exception e) {
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/signUp";
        }
    }

}
