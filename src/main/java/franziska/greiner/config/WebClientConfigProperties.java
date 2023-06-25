package franziska.greiner.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration("webClientConfigProperties")
public class WebClientConfigProperties {

    @Value("${quiz-api.base-url}")
    private String quizApiBaseUrl;

    @Value("${quiz-api.key}")
    private String quizApiKey;
}
