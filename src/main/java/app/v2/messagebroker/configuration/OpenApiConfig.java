package app.v2.messagebroker.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sensorManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Message Broker API")
                        .description("Web App Message Broker")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Atai Mamytov")
                                .email("atai.mamytov@.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub Repository")
                        .url("-"));
    }
}