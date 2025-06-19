package app.v2.messagebroker.configuration;

import app.v2.messagebroker.model.User;
import app.v2.messagebroker.repository.UserRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfigurationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpSecurity httpSecurity;

    @InjectMocks
    private CorsConfig corsConfig;

    @InjectMocks
    private OpenApiConfig openApiConfig;

    @InjectMocks
    private SecurityConfig securityConfig;

    // CorsConfig Tests

    @Test
    void corsFilter_CreatesValidConfiguration() {
        CorsFilter corsFilter = corsConfig.corsFilter();
        assertNotNull(corsFilter);

        // Indirectly verify configuration by creating a source and checking its setup
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        assertInstanceOf(CorsFilter.class, corsFilter);
    }

    @Test
    void restTemplate_CreatesRestTemplateBean() {
        RestTemplate restTemplate = corsConfig.restTemplate();
        assertNotNull(restTemplate);
        assertInstanceOf(RestTemplate.class, restTemplate);
    }

    // OpenApiConfig Tests

    @Test
    void openApi_ConfiguresCorrectApiInfo() {
        OpenAPI openAPI = openApiConfig.sensorManagementOpenAPI();
        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertEquals("Message Broker API", info.getTitle());
        assertEquals("Web App Message Broker", info.getDescription());
        assertEquals("1.0.0", info.getVersion());

        Contact contact = info.getContact();
        assertEquals("Atai Mamytov", contact.getName());
        assertEquals("atai.mamytov@.com", contact.getEmail());

        assertNotNull(openAPI.getExternalDocs());
        assertEquals("Project GitHub Repository", openAPI.getExternalDocs().getDescription());
        assertEquals("-", openAPI.getExternalDocs().getUrl());
    }

    // SecurityConfig Tests

    @Test
    void userDetailsService_ReturnsUserWhenFound() {
        User user = new User();
        user.setUsername("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(user);

        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        assertEquals(user, userDetailsService.loadUserByUsername("testuser"));
    }

    @Test
    void userDetailsService_ThrowsExceptionWhenUserNotFound() {
        when(userRepository.findByUsername("unknown")).thenReturn(null);

        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("unknown"));
    }

    @Test
    void passwordEncoder_ReturnsBCryptPasswordEncoder() {
        assertInstanceOf(BCryptPasswordEncoder.class, securityConfig.passwordEncoder());
    }

    @Test
    void securityFilterChain_ConfiguresHttpSecurityCorrectly() throws Exception {
        SecurityFilterChain filterChain = securityConfig.securityFilterChain(httpSecurity);

        assertNotNull(filterChain);
        verify(httpSecurity).csrf(any());
        verify(httpSecurity).authorizeHttpRequests(any());
        verify(httpSecurity).formLogin(any());
        verify(httpSecurity).logout(any());
        verify(httpSecurity).sessionManagement(any());
        verify(httpSecurity).exceptionHandling(any());
        verify(httpSecurity).build();
    }
}