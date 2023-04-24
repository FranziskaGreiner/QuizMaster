import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class QuizMaster {

        public static void main(String[] args) {


        }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
