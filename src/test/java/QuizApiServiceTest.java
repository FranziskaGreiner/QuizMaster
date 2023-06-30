import franziska.greiner.model.Question;
import franziska.greiner.repository.AnswerRepository;
import franziska.greiner.repository.QuestionRepository;
import franziska.greiner.service.QuizApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QuizApiServiceTest {

    @Mock
    private WebClient webClient;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;


    private QuizApiService quizApiService;
    private static final String JSON_QUESTIONS = "[{\"id\":231,\"question\":\"Which of the following SQL statements is correct?\",\"description\":null,\"answers\":{\"answer_a\":\"SELECT cname, COUNT(cname) FROM Orders\",\"answer_b\":\"SELECT cname, COUNT(cname) FROM Orders GROUP BY cname\",\"answer_c\":\"SELECT cname, COUNT(cname) FROM Orders ORDER BY cname\",\"answer_d\":null,\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"true\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"MySQL\"}],\"category\":\"SQL\",\"difficulty\":\"Hard\"},{\"id\":630,\"question\":\"What is the use of the function 'imagetypes()'?\",\"description\":null,\"answers\":{\"answer_a\":\"imagetypes() gives the image size and types supported by the current version of GH-PHP.\",\"answer_b\":\"imagetypes() gives the image format and types supported by the current version of GD-PHP.\",\"answer_c\":\"imagetypes() is not a real function\",\"answer_d\":\"imagetypes() hasn't been used since PHP4 and it's not being supported.\",\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"true\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"PHP\"}],\"category\":\"Code\",\"difficulty\":\"Medium\"},{\"id\":1059,\"question\":\"What is HTTP middleware in Laravel?\",\"description\":null,\"answers\":{\"answer_a\":\"HTTP middleware is a technique for installing Laravel via HTTP.\",\"answer_b\":\"HTTP middleware is a technique for filtering HTTP requests.\",\"answer_c\":\"HTTP middleware is a technique for updating Laravel via HTTP.\",\"answer_d\":\"HTTP middleware is a web server used by Laravel similar to Apache and Nginx.\",\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"true\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"Laravel\"}],\"category\":\"Code\",\"difficulty\":\"Easy\"},{\"id\":946,\"question\":\"Which command will you use to update a Kubernetes deployment?\",\"description\":null,\"answers\":{\"answer_a\":\"kubectl setimage deployment\\/Deployment tomcat = tomcat:6.0\",\"answer_b\":\"kubectl --setimage deployment\\/Deployment tomcat = tomcat:6.0\",\"answer_c\":\"kubectl setimage deploy\\/Deployment tomcat = tomcat:6.0\",\"answer_d\":null,\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"true\",\"answer_b_correct\":\"false\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":null,\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"Kubernetes\"}],\"category\":\"Docker\",\"difficulty\":\"Easy\"},{\"id\":407,\"question\":\"Which of the following is available in MySQL:\",\"description\":null,\"answers\":{\"answer_a\":\"CREATE VIEW\",\"answer_b\":\"CREATE DATABASE\",\"answer_c\":\"CREATE TRIGGER\",\"answer_d\":\"CREATE SCHEMA\",\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"true\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"MySQL\"}],\"category\":\"SQL\",\"difficulty\":\"Medium\"},{\"id\":829,\"question\":\"Each virtual machine includes the application, the necessary binaries and libraries, and an entire guest operating system - All of which may be tens of GBs in size.\",\"description\":null,\"answers\":{\"answer_a\":\"True\",\"answer_b\":\"False\",\"answer_c\":null,\"answer_d\":null,\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"true\",\"answer_b_correct\":\"false\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"Docker\"}],\"category\":\"Docker\",\"difficulty\":\"Medium\"},{\"id\":161,\"question\":\"In HTML, what does the <aside> element define?\",\"description\":null,\"answers\":{\"answer_a\":\"The ASCII character-set; to send information between computers on the Internet\",\"answer_b\":\"A navigation list to be shown at the left side of the page\",\"answer_c\":\"A list to be included in a certain part of the page\",\"answer_d\":\"Content aside from the page content\",\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"false\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"true\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_d\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"HTML\"}],\"category\":\"\",\"difficulty\":\"Medium\"},{\"id\":998,\"question\":\"Which of the following are Container Orchestrators? Select all answers that apply.\",\"description\":null,\"answers\":{\"answer_a\":\"Vault\",\"answer_b\":\"Ansible\",\"answer_c\":\"Kubernetes\",\"answer_d\":\"Docker Swarm\",\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"true\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"false\",\"answer_c_correct\":\"true\",\"answer_d_correct\":\"true\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":null,\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"Kubernetes\"}],\"category\":\"DevOps\",\"difficulty\":\"Easy\"},{\"id\":375,\"question\":\"Once a table has been created in MySQL, its structure cannot be altered\",\"description\":null,\"answers\":{\"answer_a\":\"True\",\"answer_b\":\"False\",\"answer_c\":null,\"answer_d\":null,\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"true\",\"answer_c_correct\":\"false\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"MySQL\"}],\"category\":\"SQL\",\"difficulty\":\"Easy\"},{\"id\":385,\"question\":\"To use 'mysqldbcopy' which privileges are required on the source server?\",\"description\":null,\"answers\":{\"answer_a\":\"CREATE\",\"answer_b\":\"UPDATE\",\"answer_c\":\"SELECT\",\"answer_d\":\"INSERT\",\"answer_e\":null,\"answer_f\":null},\"multiple_correct_answers\":\"false\",\"correct_answers\":{\"answer_a_correct\":\"false\",\"answer_b_correct\":\"false\",\"answer_c_correct\":\"true\",\"answer_d_correct\":\"false\",\"answer_e_correct\":\"false\",\"answer_f_correct\":\"false\"},\"correct_answer\":\"answer_a\",\"explanation\":null,\"tip\":null,\"tags\":[{\"name\":\"MySQL\"}],\"category\":\"SQL\",\"difficulty\":\"Medium\"}]";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.quizApiService = new QuizApiService(webClient, questionRepository, answerRepository);
    }

    @Test
    void testFetchQuestions() {
        when(webClient.method(HttpMethod.GET)).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(Function.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(JSON_QUESTIONS));

        List<Question> questions = quizApiService.fetchQuestions();

        assertNotNull(questions);
        assertEquals(5, questions.size());
    }

    @Test
    void testStoreQuestions() {
        Question question = new Question();

        when(webClient.method(HttpMethod.GET)).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(Function.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(JSON_QUESTIONS));

        doNothing().when(questionRepository).deleteAll();
        when(questionRepository.save(any(Question.class))).thenReturn(question);
        when(answerRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArguments()[0]);

        quizApiService.storeQuestions();

        verify(webClient.method(HttpMethod.GET)).uri(any(Function.class));
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToMono(String.class);
        verify(questionRepository).deleteAll();
        verify(questionRepository, times(5)).save(any(Question.class));
        verify(answerRepository, times(5)).saveAll(anyList());
    }
}
