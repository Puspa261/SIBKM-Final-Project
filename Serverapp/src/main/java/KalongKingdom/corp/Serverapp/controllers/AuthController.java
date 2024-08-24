package KalongKingdom.corp.Serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import KalongKingdom.corp.Serverapp.models.Guest;
import KalongKingdom.corp.Serverapp.models.dto.request.GuestRequest;
import KalongKingdom.corp.Serverapp.models.dto.request.LoginRequest;
import KalongKingdom.corp.Serverapp.models.dto.response.LoginResponse;
import KalongKingdom.corp.Serverapp.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthController {
    private AuthService authService;

    @PostMapping("/registration")
    public Guest registration(@RequestBody GuestRequest guestRequest) {
        return authService.registration(guestRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
