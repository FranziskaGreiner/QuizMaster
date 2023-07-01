import franziska.greiner.controller.QuizController;
import franziska.greiner.model.Question;
import franziska.greiner.service.QuizApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class QuizControllerIntegrationTest {

    @InjectMocks
    private QuizController quizController;

    @Mock
    private QuizApiService quizApiService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
    }

    @Test
    public void testGetQuizQuestions() throws Exception {
        Question question = new Question(); // fill the details of question
        when(quizApiService.fetchQuestions()).thenReturn(Arrays.asList(question));

        mockMvc.perform(get("/quiz")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(question.getId())); // assuming that id is a field in Question class

        verify(quizApiService, times(1)).fetchQuestions();
    }

    @Test
    public void testStoreQuestions() throws Exception {
        doNothing().when(quizApiService).storeQuestions();

        mockMvc.perform(post("/question")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(quizApiService, times(1)).storeQuestions();
    }
}
