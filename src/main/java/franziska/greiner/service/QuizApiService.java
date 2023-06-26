package franziska.greiner.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import franziska.greiner.model.Answer;
import franziska.greiner.model.Question;
import franziska.greiner.repository.AnswerRepository;
import franziska.greiner.repository.QuestionRepository;
import franziska.greiner.util.QuestionDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizApiService {
    private static final int LIMIT = 10;

    @Autowired
    private final WebClient webClient;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public QuizApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Question> fetchQuestions() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/questions")
                .queryParam("limit", LIMIT)
                .build());
        // Fetch as a String because WebClient does not directly support custom deserializers
        Mono<String> responseMono = bodySpec.retrieve().bodyToMono(String.class);

        // Convert String response to Question using ObjectMapper with custom deserializer
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Question.class, new QuestionDeserializer());
        objectMapper.registerModule(module);

        String response = responseMono.block();
        List<Question> questionList = null;
        try {
            questionList = objectMapper.readValue(response, new TypeReference<List<Question>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public void storeQuestions() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/questions")
                .queryParam("limit", LIMIT)
                .build());

        // Fetch as a String because WebClient does not directly support custom deserializers
        Mono<String> responseMono = bodySpec.retrieve().bodyToMono(String.class).log();

        // Convert String response to ApiResponse using ObjectMapper and then to Question using the custom deserializer
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Question.class, new QuestionDeserializer());
        objectMapper.registerModule(module);

        String response = responseMono.block();
        List<Question> questionList = null;
        try {
            questionList = objectMapper.readValue(response, new TypeReference<List<Question>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(questionList != null){
            for (Question question : questionList) {
                List<Answer> answerList = question.getAnswers();
                question.setAnswers(new ArrayList<>());
                Question insertedQuestion = questionRepository.save(question);
                for (Answer answer : answerList) {
                    answer.setQuestion(insertedQuestion);
                }
                answerRepository.saveAll(answerList);
            }
        }
    }
}
