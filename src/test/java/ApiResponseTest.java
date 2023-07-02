import franziska.greiner.model.Tag;
import franziska.greiner.util.ApiResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiResponseTest {

    @Test
    void testApiResponse() {
        Integer id = 1;
        String question = "Sample question";
        String description = "Sample description";
        Map<String, String> answers = new HashMap<>();
        answers.put("A", "Answer A");
        answers.put("B", "Answer B");
        String multipleCorrectAnswers = "false";
        Map<String, String> correctAnswers = new HashMap<>();
        correctAnswers.put("A", "Answer A");
        correctAnswers.put("B", "Answer B");
        String explanation = "Sample explanation";
        String tip = "Sample tip";
        List<Tag> tags = List.of(new Tag(), new Tag());
        String category = "Sample category";
        String difficulty = "Easy";

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setId(id);
        apiResponse.setQuestion(question);
        apiResponse.setDescription(description);
        apiResponse.setAnswers(answers);
        apiResponse.setMultipleCorrectAnswers(multipleCorrectAnswers);
        apiResponse.setCorrectAnswers(correctAnswers);
        apiResponse.setExplanation(explanation);
        apiResponse.setTip(tip);
        apiResponse.setTags(tags);
        apiResponse.setCategory(category);
        apiResponse.setDifficulty(difficulty);

        assertEquals(id, apiResponse.getId());
        assertEquals(question, apiResponse.getQuestion());
        assertEquals(description, apiResponse.getDescription());
        assertEquals(answers, apiResponse.getAnswers());
        assertEquals(multipleCorrectAnswers, apiResponse.getMultipleCorrectAnswers());
        assertEquals(correctAnswers, apiResponse.getCorrectAnswers());
        assertEquals(explanation, apiResponse.getExplanation());
        assertEquals(tip, apiResponse.getTip());
        assertEquals(tags, apiResponse.getTags());
        assertEquals(category, apiResponse.getCategory());
        assertEquals(difficulty, apiResponse.getDifficulty());
    }
}
