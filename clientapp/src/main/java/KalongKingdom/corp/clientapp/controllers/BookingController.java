package KalongKingdom.corp.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    
    @GetMapping
    public String booking(Model model) {
        model.addAttribute("isActive", "booking");
        return "menu_user/booking";
    }
}
