package app.v2.messagebroker.controller;

import app.v2.messagebroker.model.App;
import app.v2.messagebroker.model.User;
import app.v2.messagebroker.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;
    private final RestTemplate restTemplate;

    @GetMapping("/app/create")
    @Operation(summary = "Show create app form", description = "Displays the form to create a new application")
    public String showCreateAppForm() {
        return "create-app";
    }

    @PostMapping("/app/create")
    @Operation(summary = "Create a new app", description = "Creates a new application with name, GitHub token, and prompt")
    @ApiResponse(responseCode = "302", description = "Redirects to app list")
    public String createApp(@RequestParam String name, @RequestParam String githubToken,
                            @RequestParam String prompt, @AuthenticationPrincipal User user) {
        appService.createApp(name, githubToken, prompt, user);
        return "redirect:/home";
    }

    @PostMapping("/app/delete/{id}")
    @Operation(summary = "Delete app", description = "Deletes the application with the given ID")
    public String deleteApp(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<App> appOpt = appService.findById(id);
        if (appOpt.isPresent() && appOpt.get().getUser().getId().equals(user.getId())) {
            appService.deleteAppById(id);
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    @Operation(summary = "List user apps", description = "Displays the list of applications for the authenticated user")
    public String listApps(@AuthenticationPrincipal User user, Model model) {
        List<App> apps = appService.findByUserId(user.getId());
        model.addAttribute("apps", apps);
        return "home";
    }

    @GetMapping("/app/{id}")
    @Operation(summary = "View app detail", description = "Show detailed info for an app")
    public String appDetail(@PathVariable Long id, @AuthenticationPrincipal User user, Model model) {
        Optional<App> appOpt = appService.findById(id);
        if (appOpt.isEmpty() || !appOpt.get().getUser().getId().equals(user.getId())) {
            return "redirect:/home";
        }
        model.addAttribute("app", appOpt.get());
        return "app-detail";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/api/forward")
    @ResponseBody
    @Operation(summary = "Forward request to microservice", description = "Forwards repository ID, GitHub token, and prompt to a microservice")
    @ApiResponse(responseCode = "200", description = "Request forwarded successfully")
    @ApiResponse(responseCode = "401", description = "Invalid app token")
    @ApiResponse(responseCode = "500", description = "Failed to forward request")
    public ResponseEntity<String> forwardRequest(@RequestParam String appToken, @RequestParam String repositoryName,  @RequestParam String currentCommit,  @RequestParam String prevCommit, @RequestParam String pusherName) {
        Optional<App> appOpt = appService.findByAppToken(appToken);
        if (appOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid app token");
        }

        App app = appOpt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String payload = String.format(
                "{\"githubToken\": \"%s\", \"prompt\": \"%s\", \"repository\": \"%s\", \"commit\": \"%s\", \"previous_commit\": \"%s\", \"pusher\": \"%s\"}",
                app.getGithubToken(), app.getPrompt(), repositoryName, currentCommit, prevCommit, pusherName
        );
        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        try {
            System.out.println("Sending request to microservice");
            ResponseEntity<String> response = restTemplate.postForEntity("http://process-api:8082/process", request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to forward request: " + e.getMessage());
        }
    }
}