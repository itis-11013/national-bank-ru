package ru.itis.nationalbankru.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.services.user.UserService;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @ApiOperation(value = "SignUp an organization")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Sign", response = UserResponseDto.class),
            @ApiResponse(code = 401, message = "Authorization required"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server error")
    })

    @PostMapping("/new")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

}
