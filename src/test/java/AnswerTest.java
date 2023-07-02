import franziska.greiner.model.Answer;
import franziska.greiner.model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AnswerTest {

    @Test
    void testAnswerInitialization() {
        Question question = new Question();
        question.setId(1);
        question.setQuestion("Sample question");

        Answer answer = new Answer();
        answer.setId(1);
        answer.setAnswer("Sample answer");
        answer.setCorrect(true);
        answer.setQuestion(question);

        assertEquals(1, answer.getId());
        assertEquals("Sample answer", answer.getAnswer());
        assertEquals(true, answer.getCorrect());
        assertNotNull(answer.getQuestion());
        assertEquals(1, answer.getQuestion().getId());
        assertEquals("Sample question", answer.getQuestion().getQuestion());
    }
}
