package KalongKingdom.corp.clientapp.controllers.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.clientapp.models.Guest;
import KalongKingdom.corp.clientapp.services.ProfileService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/profile")
public class RestProfileController {
    
    private ProfileService profileService;

    @GetMapping
    public Guest getProfile(Authentication auth) {
        return profileService.getProfile(auth.getName());
    }
}
