package app.v2.messagebroker.service;

import app.v2.messagebroker.model.App;
import app.v2.messagebroker.model.User;
import app.v2.messagebroker.repository.AppRepository;
import app.v2.messagebroker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

    @Mock
    private AppRepository appRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppService appService;

    @InjectMocks
    private UserService userService;

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
        app.setUser(user);
    }

    // AppService Tests

    @Test
    void createApp_SavesAndReturnsApp() {
        when(appRepository.save(any(App.class))).thenReturn(app);

        App createdApp = appService.createApp("TestApp", "gh_token", "Test prompt", user);

        assertNotNull(createdApp);
        assertEquals("TestApp", createdApp.getName());
        verify(appRepository).save(any(App.class));
    }

    @Test
    void findByAppToken_ReturnsAppWhenFound() {
        when(appRepository.findByAppToken("app_token")).thenReturn(Optional.of(app));

        Optional<App> result = appService.findByAppToken("app_token");

        assertTrue(result.isPresent());
        assertEquals(app, result.get());
    }

    @Test
    void findByAppToken_ReturnsEmptyWhenNotFound() {
        when(appRepository.findByAppToken("invalid_token")).thenReturn(Optional.empty());

        Optional<App> result = appService.findByAppToken("invalid_token");

        assertFalse(result.isPresent());
    }

    @Test
    void findByUserId_ReturnsListOfApps() {
        when(appRepository.findByUserId(1L)).thenReturn(Collections.singletonList(app));

        List<App> apps = appService.findByUserId(1L);

        assertFalse(apps.isEmpty());
        assertEquals(1, apps.size());
        assertEquals(app, apps.get(0));
    }

    @Test
    void findById_ReturnsAppWhenFound() {
        when(appRepository.findById(1L)).thenReturn(Optional.of(app));

        Optional<App> result = appService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(app, result.get());
    }

    @Test
    void findById_ReturnsEmptyWhenNotFound() {
        when(appRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<App> result = appService.findById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void deleteAppById_CallsRepositoryDelete() {
        appService.deleteAppById(1L);

        verify(appRepository).deleteById(1L);
    }

    // UserService Tests

    @Test
    void registerUser_SuccessfulRegistrationReturnsTrue() {
        when(userRepository.findByUsername("newuser")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        boolean result = userService.registerUser("newuser", "password", "USER");

        assertTrue(result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_UsernameExistsReturnsFalse() {
        User existingUser = new User();
        existingUser.setUsername("existinguser");
        when(userRepository.findByUsername("existinguser")).thenReturn(existingUser);

        boolean result = userService.registerUser("existinguser", "password", "USER");

        assertFalse(result);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_EncodesPasswordAndSetsRole() {
        when(userRepository.findByUsername("newuser")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        userService.registerUser("newuser", "password", "user");

        verify(passwordEncoder).encode("password");
        verify(userRepository).save(argThat(user ->
                user.getUsername().equals("newuser") &&
                        user.getPassword().equals("encodedPassword") &&
                        user.getRole().equals("USER")
        ));
    }
}