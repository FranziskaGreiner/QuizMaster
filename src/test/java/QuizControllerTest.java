import franziska.greiner.controller.QuizController;
import franziska.greiner.service.QuizApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {
    @InjectMocks
    private QuizController quizController;

    @Mock
    private QuizApiService quizApiService;

    @Test
    void storeQuestions_shouldReturnOkStatus() {
        doNothing().when(quizApiService).storeQuestions();

        ResponseEntity<Void> response = quizController.storeQuestions();

        verify(quizApiService, times(1)).storeQuestions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
