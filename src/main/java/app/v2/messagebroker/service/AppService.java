package app.v2.messagebroker.service;

import app.v2.messagebroker.model.App;
import app.v2.messagebroker.model.User;
import app.v2.messagebroker.repository.AppRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public App createApp(String name, String githubToken, String prompt, User user) {
        App app = new App();
        app.setName(name);
        app.setGithubToken(githubToken);
        app.setPrompt(prompt);
        app.setUser(user);
        return appRepository.save(app);
    }

    public Optional<App> findByAppToken(String appToken) {
        return appRepository.findByAppToken(appToken);
    }

    public List<App> findByUserId(Long userId) {
        return appRepository.findByUserId(userId);
    }

    public Optional<App> findById(Long id) {
        return appRepository.findById(id);
    }

    public void deleteAppById(Long id) {
        appRepository.deleteById(id);
    }
}