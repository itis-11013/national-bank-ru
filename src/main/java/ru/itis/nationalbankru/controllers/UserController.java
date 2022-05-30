package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.entity.enums.RequestStatus;
import ru.itis.nationalbankru.services.user.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<GeneralResponse<?>> createUser(
            @RequestBody UserRequestDto userRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return ResponseEntity.ok(new GeneralResponse<UserResponseDto>().toBuilder()
                    .description("Successfully Created User")
                    .status(RequestStatus.SUCCESS)
                    .data(userResponseDto)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Create User")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<?>> updateUserWithId(
            @PathVariable UUID id,
            @RequestBody UserRequestDto userRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            UserResponseDto userResponseDto = userService.updateUserWithId(id, userRequestDto);
            return ResponseEntity.ok(new GeneralResponse<UserResponseDto>().toBuilder()
                    .description("Successfully Updated User")
                    .status(RequestStatus.SUCCESS)
                    .data(userResponseDto)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Update User")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<?>> deleteUserWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            UUID uuid = userService.deleteUserWithId(id);
            return ResponseEntity.ok(new GeneralResponse<UUID>().toBuilder()
                    .description("Successfully Deleted User")
                    .status(RequestStatus.SUCCESS)
                    .data(uuid)
                    .build());
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse<>().toBuilder()
                    .description("Failed To Delete User")
                    .status(RequestStatus.FAILURE)
                    .build());
        }
    }
}
