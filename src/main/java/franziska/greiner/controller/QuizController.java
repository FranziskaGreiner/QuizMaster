package franziska.greiner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import franziska.greiner.model.Question;
import franziska.greiner.service.QuizApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    private static final String API_URL = "https://quizapi.io/api/v1/questions";
    private static final String API_KEY = "78Kd7bqZLyRKpUbVoxSSnSzmzq2lM6IrGTTaXACW";
    private static final int LIMIT = 10;

    @Autowired
    private QuizApiService quizApiService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/quiz")
    public List<Question> getQuizQuestions() throws JsonProcessingException {
        /*ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url = API_URL + "?apiKey=" + API_KEY + "&limit=" + LIMIT;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
        return objectMapper.readValue(response, new TypeReference<List<Question>>() {});*/
        return quizApiService.fetchQuestions();
    }

    @PostMapping("/question")
    public ResponseEntity<Void> storeQuestions() {
        quizApiService.storeQuestions();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
