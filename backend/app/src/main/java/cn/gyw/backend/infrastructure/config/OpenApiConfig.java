package cn.gyw.backend.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger open api config
 *
 * @date 2023/3/16
 */
@Configuration
public class OpenApiConfig {

    /**
     * 允许使用或不使用授权头来测试端点
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("OpenAPI").version("1.0.0"))
                // Components section defines Security Scheme "secretHeader"
                .components(new Components()
                        .addSecuritySchemes("secretHeader", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("token")))
                // AddSecurityItem section applies created scheme globally
                .addSecurityItem(new SecurityRequirement().addList("secretHeader"));
    }
}
