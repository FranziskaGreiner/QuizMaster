import franziska.greiner.model.Answer;
import franziska.greiner.model.Difficulty;
import franziska.greiner.model.Question;
import franziska.greiner.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuestionTest {

    @Test
    void testQuestionInitialization() {
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setName("tag1");
        tags.add(tag1);
        Tag tag2 = new Tag();
        tag2.setId(2);
        tag2.setName("tag2");
        tags.add(tag2);

        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setId(1);
        answer1.setAnswer("answer1");
        answers.add(answer1);
        Answer answer2 = new Answer();
        answer2.setId(2);
        answer2.setAnswer("answer2");
        answers.add(answer2);

        Question question = new Question();
        question.setId(1);
        question.setQuestion("Sample question");
        question.setDescription("Sample description");
        question.setMultipleCorrectAnswers(true);
        question.setExplanation("Sample explanation");
        question.setTip("Sample tip");
        question.setTags(tags);
        question.setCategory("Sample category");
        question.setDifficulty(Difficulty.EASY);
        question.setAnswers(answers);

        assertEquals(1, question.getId());
        assertEquals("Sample question", question.getQuestion());
        assertEquals("Sample description", question.getDescription());
        assertEquals(true, question.getMultipleCorrectAnswers());
        assertEquals("Sample explanation", question.getExplanation());
        assertEquals("Sample tip", question.getTip());
        assertNotNull(question.getTags());
        assertEquals(2, question.getTags().size());
        assertEquals(1, question.getTags().get(0).getId());
        assertEquals("tag1", question.getTags().get(0).getName());
        assertEquals(2, question.getTags().get(1).getId());
        assertEquals("tag2", question.getTags().get(1).getName());
        assertEquals("Sample category", question.getCategory());
        assertEquals(Difficulty.EASY, question.getDifficulty());
        assertNotNull(question.getAnswers());
        assertEquals(2, question.getAnswers().size());
        assertEquals(1, question.getAnswers().get(0).getId());
        assertEquals("answer1", question.getAnswers().get(0).getAnswer());
        assertEquals(2, question.getAnswers().get(1).getId());
        assertEquals("answer2", question.getAnswers().get(1).getAnswer());
    }
}
