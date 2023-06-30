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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class QuizApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizApiService.class);
    private static final int LIMIT = 10;

    private final WebClient webClient;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuizApiService(WebClient webClient, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.webClient = webClient;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public List<Question> fetchQuestions() {
        String response = getApiResponse();
        return deserializeResponse(response);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void storeQuestions() {
        String response = getApiResponse();
        List<Question> questionList = deserializeResponse(response);

        // Delete all questions before saving new ones
        questionRepository.deleteAll();

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

    private String getApiResponse() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/questions")
                .queryParam("limit", LIMIT)
                .build());

        // Fetch as a String because WebClient does not directly support custom deserializers
        return bodySpec.retrieve().bodyToMono(String.class).block();
    }

    private List<Question> deserializeResponse(String response) {
        // Convert String response to Question using ObjectMapper with custom deserializer
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Question.class, new QuestionDeserializer());
        objectMapper.registerModule(module);

        try {
            return objectMapper.readValue(response, new TypeReference<List<Question>>(){});
        } catch (JsonProcessingException e) {
            LOGGER.error("Error during deserialization of response", e);
            return null;
        }
    }
}
