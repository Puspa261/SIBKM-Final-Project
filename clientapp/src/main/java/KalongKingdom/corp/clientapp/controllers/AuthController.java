package KalongKingdom.corp.clientapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import KalongKingdom.corp.clientapp.models.dto.request.GuestRequest;
import KalongKingdom.corp.clientapp.models.dto.request.LoginRequest;
import KalongKingdom.corp.clientapp.services.AuthService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private AuthService authService;

    @GetMapping
    public String loginView(LoginRequest loginRequest) {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }
        return "redirect:/home";
    }

    @PostMapping
    public String login(LoginRequest loginRequest) {
        if (!authService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        List<String> roles = auth
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        for (String role : roles) {
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            }
        }
        return "redirect:/home";
    }

    // @GetMapping("/signup")
    // public String registerUserView(Model model) {
    // model.addAttribute("guestRequest", new GuestRequest());

    // return "Auth/login";
    // }

    // @PostMapping("/registration")
    // public String registUser(
    // GuestRequest guestRequest,
    // RedirectAttributes ra
    // ) {
    // ra.addFlashAttribute("message", "Your registration success, please login!");
    // authService.registerUser(guestRequest);

    // return "redirect:/login";
    // }

}
