package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "ADV Shop"); // Mengirim title ke Thymeleaf
        return "HomePage"; // Pastikan nama ini sesuai dengan HomePage.html
    }
}
