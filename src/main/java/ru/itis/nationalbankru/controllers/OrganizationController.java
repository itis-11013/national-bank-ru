package ru.itis.nationalbankru.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.nationalbankru.dto.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.OrganizationResponseDto;
import ru.itis.nationalbankru.services.OrganizationService;

@RestController("/organization")
public class OrganizationController {

    public final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Creation of an organization")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created", response = OrganizationResponseDto.class),
            @ApiResponse(code = 401, message = "Authorization required"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/new")
    public ResponseEntity<OrganizationResponseDto> createOrganization(@RequestBody OrganizationRequestDto organizationRequestDto) {
        return ResponseEntity.ok(organizationService.createOrganization(organizationRequestDto));
    }
}
