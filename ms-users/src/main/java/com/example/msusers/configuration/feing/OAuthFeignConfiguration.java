package com.example.msusers.configuration.feing;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

// FIXME: solo se usa en el caso de llamada servicio a servicio
@Configuration
public class OAuthFeignConfiguration {
	public static final String CLIENT_REGISTRATION_ID = "keycloak";

	private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
	private final ClientRegistrationRepository clientRegistrationRepository;

	public OAuthFeignConfiguration(OAuth2AuthorizedClientService oAuth2AuthorizedClientService,
                                   ClientRegistrationRepository clientRegistrationRepository) {
		this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	// FIXME: solo se usa en el caso de llamada servicio a servicio
	@Bean
	public RequestInterceptor requestInterceptor() {
		ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID);
		OAuthClientCredentialsFeignManager clientCredentialsFeignManager =
				new OAuthClientCredentialsFeignManager(authorizedClientManager(), clientRegistration);
		String token = clientCredentialsFeignManager.getAccessToken();
		return requestTemplate -> {
			requestTemplate.header("Authorization", "Bearer " + token);
		};
	}

	// FIXME: solo se usa en el caso de llamada servicio a servicio
	@Bean
	OAuth2AuthorizedClientManager authorizedClientManager() {
		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();

		AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
				new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientService);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		return authorizedClientManager;
	}
}
