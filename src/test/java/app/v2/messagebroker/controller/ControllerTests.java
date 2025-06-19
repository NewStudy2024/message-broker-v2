package app.v2.messagebroker.controller;

import app.v2.messagebroker.model.App;
import app.v2.messagebroker.model.User;
import app.v2.messagebroker.service.AppService;
import app.v2.messagebroker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ControllerTests {

    @Mock
    private AppService appService;

    @Mock
    private UserService userService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Model model;

    @InjectMocks
    private AppController appController;

    @InjectMocks
    private WebController webController;

    private User user;
    private App app;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        app = new App();
        app.setId(1L);
        app.setName("TestApp");
        app.setGithubToken("gh_token");
        app.setPrompt("Test prompt");
        app.setAppToken("app_token");
        app.setUser(user);
    }

    // AppController Tests

    @Test
    void showCreateAppForm_ReturnsCreateAppView() {
        String view = appController.showCreateAppForm();
        assertEquals("create-app", view);
    }

    @Test
    void createApp_Success_RedirectsToHome() {
        String redirect = appController.createApp("TestApp", "gh_token", "prompt", user);
        verify(appService).createApp(anyString(), anyString(), anyString(), any(User.class));
        assertEquals("redirect:/home", redirect);
    }

    @Test
    void deleteApp_UserOwnsApp_RedirectsToHome() {
        when(appService.findById(1L)).thenReturn(Optional.of(app));
        String redirect = appController.deleteApp(1L, user);
        verify(appService).deleteAppById(1L);
        assertEquals("redirect:/home", redirect);
    }

    @Test
    void deleteApp_UserDoesNotOwnApp_RedirectsToHome() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        when(appService.findById(1L)).thenReturn(Optional.of(app));
        String redirect = appController.deleteApp(1L, anotherUser);
        verify(appService, never()).deleteAppById(anyLong());
        assertEquals("redirect:/home", redirect);
    }

    @Test
    void listApps_ReturnsHomeViewWithApps() {
        when(appService.findByUserId(1L)).thenReturn(Arrays.asList(app));
        String view = appController.listApps(user, model);
        verify(model).addAttribute("apps", Arrays.asList(app));
        assertEquals("home", view);
    }

    @Test
    void appDetail_AppExistsAndUserOwns_ReturnsAppDetailView() {
        when(appService.findById(1L)).thenReturn(Optional.of(app));
        String view = appController.appDetail(1L, user, model);
        verify(model).addAttribute("app", app);
        assertEquals("app-detail", view);
    }

    @Test
    void appDetail_AppDoesNotExist_RedirectsToHome() {
        when(appService.findById(1L)).thenReturn(Optional.empty());
        String view = appController.appDetail(1L, user, model);
        assertEquals("redirect:/home", view);
    }

    @Test
    void appDetail_UserDoesNotOwnApp_RedirectsToHome() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        when(appService.findById(1L)).thenReturn(Optional.of(app));
        String view = appController.appDetail(1L, anotherUser, model);
        assertEquals("redirect:/home", view);
    }

    @Test
    void forwardRequest_ValidAppToken_Success() {
        when(appService.findByAppToken("app_token")).thenReturn(Optional.of(app));
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        ResponseEntity<String> response = appController.forwardRequest(
                "app_token", "repo", "commit", "prev_commit", "pusher");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    void forwardRequest_InvalidAppToken_ReturnsUnauthorized() {
        when(appService.findByAppToken("invalid_token")).thenReturn(Optional.empty());

        ResponseEntity<String> response = appController.forwardRequest(
                "invalid_token", "repo", "commit", "prev_commit", "pusher");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid app token", response.getBody());
    }

    @Test
    void forwardRequest_MicroserviceError_ReturnsInternalServerError() {
        when(appService.findByAppToken("app_token")).thenReturn(Optional.of(app));
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class)))
                .thenThrow(new RuntimeException("Microservice error"));

        ResponseEntity<String> response = appController.forwardRequest(
                "app_token", "repo", "commit", "prev_commit", "pusher");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to forward request: Microservice error", response.getBody());
    }

    @Test
    void login_ReturnsLoginView() {
        String view = webController.login();
        assertEquals("login", view);
    }

    @Test
    void showInstructions_ReturnsInstructionsView() {
        String view = webController.showInstructions();
        assertEquals("instructions", view);
    }

    @Test
    void showRegisterForm_ReturnsRegisterView() {
        String view = webController.showRegisterForm();
        assertEquals("register", view);
    }

    @Test
    void register_Success_RedirectsToLogin() {
        when(userService.registerUser(anyString(), anyString(), anyString())).thenReturn(true);
        String redirect = webController.register("newuser", "password", model);
        assertEquals("redirect:/login?registered", redirect);
    }

    @Test
    void register_UsernameExists_ReturnsRegisterViewWithError() {
        when(userService.registerUser(anyString(), anyString(), anyString())).thenReturn(false);
        String view = webController.register("existinguser", "password", model);
        verify(model).addAttribute("error", "Username already exists");
        assertEquals("register", view);
    }
}