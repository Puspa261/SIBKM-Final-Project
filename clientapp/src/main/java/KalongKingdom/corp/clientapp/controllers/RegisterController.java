package KalongKingdom.corp.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import KalongKingdom.corp.clientapp.models.dto.request.GuestRequest;
import KalongKingdom.corp.clientapp.services.RegisterService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private RegisterService registerService;

    @GetMapping
    public String registerUserView(Model model) {
        model.addAttribute("guestRequest", new GuestRequest());

        return "Auth/register";
    }

    @PostMapping
    public String registUser(
            GuestRequest guestRequest,
            RedirectAttributes ra) {
        ra.addFlashAttribute("message", "Your registration success, please  login!");
        registerService.registerUser(guestRequest);

        return "redirect:/login";
    }

}
