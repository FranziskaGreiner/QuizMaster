package franziska.greiner.controller;

import franziska.greiner.model.Question;
import franziska.greiner.service.QuizApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {

    @Autowired
    private QuizApiService quizApiService;

    @GetMapping("/quiz")
    public List<Question> getQuizQuestions() {
        return quizApiService.fetchQuestions();
    }

    @GetMapping("/question")
    public ResponseEntity<Void> storeQuestions() {
        quizApiService.storeQuestions();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
