package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.nationalbankru.dto.user.UserRequestDto;
import ru.itis.nationalbankru.dto.user.UserResponseDto;
import ru.itis.nationalbankru.services.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.createUser(userRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserWithId(@PathVariable Long id,
                                                            @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUserWithId(id, userRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUserWithId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserWithId(id));
    }


}
