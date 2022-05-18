package ru.itis.nationalbankru.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.services.user.UserServiceImpl;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private UserServiceImpl userService;

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
        userService.createUser(userRequestDto);
        return "redirect:/auth/signIn";
    }

}
