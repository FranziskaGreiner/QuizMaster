package controller;

import entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
    private static final String API_URL = "https://quizapi.io/api/v1/questions";
    private static final String API_KEY = "78Kd7bqZLyRKpUbVoxSSnSzmzq2lM6IrGTTaXACW";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/quiz")
    public List<Question> getQuizQuestions() {
        String url = API_URL + "?apiKey=" + API_KEY + "&limit=10";
        ResponseEntity<Question[]> response = restTemplate.getForEntity(url, Question[].class);
        Question[] questions = response.getBody();
        return Arrays.asList(questions);
    }
}
