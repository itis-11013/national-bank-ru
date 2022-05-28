package ru.itis.nationalbankru.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.services.user.UserServiceImpl;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in_page";
    }

    @ApiOperation(value = "Create User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Sign", response = UserResponseDto.class),
            @ApiResponse(code = 401, message = "Authorization required"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(UserRequestDto userRequestDto) {
        try {
            userService.createUser(userRequestDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/auth/signUp";
        }
        return "redirect:/auth/signIn";
    }

}
