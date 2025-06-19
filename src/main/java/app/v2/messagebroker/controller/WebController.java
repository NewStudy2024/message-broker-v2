package app.v2.messagebroker.controller;

import app.v2.messagebroker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final UserService userService;

    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/instructions")
    public String showInstructions() {
        return "instructions";
    }

    @GetMapping("/register")
    @Operation(summary = "Show registration form", description = "Displays the registration form")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user with username, password, and role")
    @ApiResponse(responseCode = "302", description = "Redirects to login page on success or back to register on failure")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        boolean registered = userService.registerUser(username, password, "USER");
        if (registered) {
            return "redirect:/login?registered";
        } else {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
    }
}