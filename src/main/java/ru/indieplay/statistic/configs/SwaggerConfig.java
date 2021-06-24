package ru.indieplay.statistic.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by Fadesml on 23.05.2021.
 */
@Configuration
class SwaggerConfig {
    @Bean
    public Docket api()  {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.indieplay.statistic.controllers"))
                .paths(PathSelectors.any())
                .build();
    }
}
