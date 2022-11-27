package ru.itis.nationalbankru.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.nationalbankru.dto.organization.OrganizationResponseDto;
import ru.itis.nationalbankru.services.organization.OrganizationService;

/**
 * @author : Escalopa
 * @created : 09.06.2022, Thu
 * @time : 16:59
 **/

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final OrganizationService organizationService;

    @GetMapping("")
    public String homePage(ModelMap map) {
        OrganizationResponseDto currentUser = organizationService.getCurrentUser();
        map.addAttribute("currentUser", currentUser);
        return "profile_page";
    }
}
