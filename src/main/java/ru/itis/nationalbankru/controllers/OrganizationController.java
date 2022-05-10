package ru.itis.nationalbankru.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.nationalbankru.dto.OrganizationRequestDto;
import ru.itis.nationalbankru.dto.OrganizationResponseDto;
import ru.itis.nationalbankru.services.OrganizationService;

@RestController("/org")
public class OrganizationController {

    public final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @PostMapping("/new")
    public ResponseEntity<OrganizationResponseDto> createOrganization(@RequestBody OrganizationRequestDto organizationRequestDto) {
        // Response Error
        organizationService.createOrganization(organizationRequestDto);
        return null;
    }
}
