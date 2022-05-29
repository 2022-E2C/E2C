package E2C.project.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api2() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("E2C.project"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("E2C Swagger")
                        .description("Specification")
                        .version("1.0")
                        .build());
    }
}
