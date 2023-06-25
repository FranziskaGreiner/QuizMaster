package franziska.greiner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired
    private WebClientConfigProperties webClientConfigProperties;

    @Bean
    public WebClient webClient() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add("X-Api-Key", webClientConfigProperties.getQuizApiKey());

        return WebClient.builder()
                .baseUrl(webClientConfigProperties.getQuizApiBaseUrl())
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(headers))
                .build();
    }
}
