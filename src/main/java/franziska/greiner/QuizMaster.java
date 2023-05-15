package franziska.greiner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class QuizMaster {

        public static void main(String[] args) {
            SpringApplication.run(QuizMaster.class, args);
        }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
