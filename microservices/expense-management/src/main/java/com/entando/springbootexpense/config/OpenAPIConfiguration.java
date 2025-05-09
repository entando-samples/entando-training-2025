package com.entando.springbootexpense.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "Agenda",
        description = "Agenda REST API browser", version = "v1"))
@SecurityScheme(name = "agenda_auth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${springdoc.oauth-flow.authorizationUrl}",
                tokenUrl = "${springdoc.oauth-flow.tokenUrl}"
        )))
public class OpenAPIConfiguration { }
