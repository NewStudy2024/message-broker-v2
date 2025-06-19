package app.v2.messagebroker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ModelTests {

    private App app;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        user.setApps(Collections.emptyList());

        app = new App();
        app.setId(1L);
        app.setName("TestApp");
        app.setGithubToken("gh_token");
        app.setPrompt("Test prompt");
        app.setUser(user);
    }

    // App Tests

    @Test
    void app_DefaultConstructor_CreatesInstance() {
        App newApp = new App();
        assertNotNull(newApp);
    }

    @Test
    void app_AllArgsConstructor_CreatesInstanceWithValues() {
        App newApp = new App(1L, "TestApp", "gh_token", "app_token", "Test prompt", user);
        assertNotNull(newApp);
        assertEquals(1L, newApp.getId());
        assertEquals("TestApp", newApp.getName());
        assertEquals("gh_token", newApp.getGithubToken());
        assertEquals("app_token", newApp.getAppToken());
        assertEquals("Test prompt", newApp.getPrompt());
        assertEquals(user, newApp.getUser());
    }

    @Test
    void app_GenerateAppToken_SetsUniqueTokenOnPrePersist() {
        App newApp = new App();
        newApp.setName("NewApp");
        newApp.setGithubToken("new_token");
        newApp.setPrompt("New prompt");
        newApp.setUser(user);

        // Simulate @PrePersist by calling generateAppToken manually
        newApp.generateAppToken();

        assertNotNull(newApp.getAppToken());
        assertTrue(UUID.fromString(newApp.getAppToken()) instanceof UUID);
        assertNotEquals("", newApp.getAppToken());
    }

    @Test
    void app_SettersAndGetters_WorkCorrectly() {
        app.setName("UpdatedApp");
        app.setGithubToken("new_gh_token");
        app.setPrompt("Updated prompt");
        User newUser = new User();
        newUser.setId(2L);
        app.setUser(newUser);

        assertEquals("UpdatedApp", app.getName());
        assertEquals("new_gh_token", app.getGithubToken());
        assertEquals("Updated prompt", app.getPrompt());
        assertEquals(newUser, app.getUser());
    }

    // User Tests

    @Test
    void user_DefaultConstructor_CreatesInstance() {
        User newUser = new User();
        assertNotNull(newUser);
    }

    @Test
    void user_AllArgsConstructor_CreatesInstanceWithValues() {
        User newUser = new User(1L, "testuser", "password", "USER", Collections.emptyList());
        assertNotNull(newUser);
        assertEquals(1L, newUser.getId());
        assertEquals("testuser", newUser.getUsername());
        assertEquals("password", newUser.getPassword());
        assertEquals("USER", newUser.getRole());
        assertNotNull(newUser.getApps());
    }

    @Test
    void user_GetAuthorities_ReturnsRoleAuthority() {
        assertEquals(1, user.getAuthorities().size());
        assertEquals("ROLE_USER", user.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void user_UserDetailsMethods_ReturnTrue() {
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    void user_SettersAndGetters_WorkCorrectly() {
        user.setUsername("newuser");
        user.setPassword("newpassword");
        user.setRole("ADMIN");
        user.setApps(Collections.singletonList(app));

        assertEquals("newuser", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertEquals("ADMIN", user.getRole());
        assertEquals(1, user.getApps().size());
        assertEquals(app, user.getApps().get(0));
    }
}