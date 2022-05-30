package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.nationalbankru.dto.GeneralResponse;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseClass;
import ru.itis.nationalbankru.dto.GeneralResponse.ResponseDescription;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.services.user.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<GeneralResponse<UserResponseDto>> createUser(
            @RequestBody UserRequestDto userRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return new GeneralResponse<UserResponseDto>().setSuccessResponse(
                    userResponseDto,
                    ResponseDescription.created,
                    ResponseClass.user);
        } catch (Exception exception) {
            return new GeneralResponse<UserResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.create,
                    ResponseClass.user);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<UserResponseDto>> updateUserWithId(
            @PathVariable UUID id,
            @RequestBody UserRequestDto userRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            UserResponseDto userResponseDto = userService.updateUserWithId(id, userRequestDto);
            return new GeneralResponse<UserResponseDto>().setSuccessResponse(
                    userResponseDto,
                    ResponseDescription.updated,
                    ResponseClass.user);
        } catch (Exception exception) {
            return new GeneralResponse<UserResponseDto>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.update,
                    ResponseClass.user);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<UUID>> deleteUserWithId(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes) {
        try {
            UUID uuid = userService.deleteUserWithId(id);
            return new GeneralResponse<UUID>().setSuccessResponse(
                    uuid,
                    ResponseDescription.deleted,
                    ResponseClass.user);
        } catch (Exception exception) {
            return new GeneralResponse<UUID>().setFailureResponse(
                    redirectAttributes,
                    exception,
                    ResponseDescription.delete,
                    ResponseClass.user);
        }
    }
}
