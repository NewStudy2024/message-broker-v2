package app.v2.messagebroker.repository;

import app.v2.messagebroker.model.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findByAppToken(String appToken);
    List<App> findByUserId(Long userId);
}