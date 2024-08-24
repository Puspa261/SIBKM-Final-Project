package KalongKingdom.corp.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, Authentication auth) {
        model.addAttribute("name", auth.getName());
        model.addAttribute("isActive", "home");
        return "menu_user/dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Authentication auth) {
        model.addAttribute("name", auth.getName());
        // model.addAttribute("role", auth.getDetails().toString());
        model.addAttribute("isActive", "admin");
        return "menu_admin/dashboard";
    }

    @GetMapping("/book")
    public String book() {
        return "menu_admin/book";
    }

    @GetMapping("/kategori")
    public String category() {
        return "menu_admin/kategori";
    }

    @GetMapping("/admins")
    public String admins() {
        return "menu_admin/admin";
    }

    @GetMapping("/createBook")
    public String createBook() {
        return "menu_admin/createBook";
    }

    @GetMapping("/updateBook")
    public String updateBook() {
        return "menu_admin/updateBook";
    }

    @GetMapping("/history")
    public String history() {
        return "menu_user/history";
    }

    @GetMapping("/historyAdmin")
    public String historyAdmin() {
        return "menu_admin/history";
    }

}
