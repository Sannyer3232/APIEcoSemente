package br.edu.ifam.ecosemente.ecosemente.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info( new Info()
                        .title("Documentação da API do aplicativo EcoSemente")
                        .version("1.0.0")
                        .description("Documentação interativa da EcoSemente API usando Swagger/OpenAPI"));

    }
}
