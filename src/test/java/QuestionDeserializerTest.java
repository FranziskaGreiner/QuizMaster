import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import franziska.greiner.model.Difficulty;
import franziska.greiner.model.Question;
import franziska.greiner.util.ApiResponse;
import franziska.greiner.util.QuestionDeserializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class QuestionDeserializerTest {

    @Test
    void deserializeTest() throws IOException {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setId(123);
        apiResponse.setQuestion("Which of the following SQL statements is correct?");
        apiResponse.setDescription(null);
        apiResponse.setExplanation(null);
        apiResponse.setTip(null);
        apiResponse.setCategory("SQL");
        apiResponse.setDifficulty("Hard");
        apiResponse.setMultipleCorrectAnswers("false");
        apiResponse.setCorrectAnswers(Map.of("answer_a_correct", "false", "answer_b_correct", "true", "answer_c_correct", "false"));
        apiResponse.setAnswers(Map.of("answer_a", "SELECT cname, COUNT(cname) FROM Orders", "answer_b", "SELECT cname, COUNT(cname) FROM Orders GROUP BY cname", "answer_c", "SELECT cname, COUNT(cname) FROM Orders ORDER BY cname"));

        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode rootNode = factory.objectNode();

        ArrayNode tagsNode = factory.arrayNode();
        ObjectNode tagNode = factory.objectNode();
        tagNode.put("name", "SQL");
        tagsNode.add(tagNode);
        rootNode.set("tags", tagsNode);

        ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
        JsonParser jsonParser = Mockito.mock(JsonParser.class);

        Mockito.when(jsonParser.getCodec()).thenReturn(mapper);
        Mockito.when(jsonParser.readValueAs(ApiResponse.class)).thenReturn(apiResponse);
        Mockito.when(mapper.readTree(jsonParser)).thenReturn(rootNode);

        QuestionDeserializer deserializer = new QuestionDeserializer();
        Question question = deserializer.deserialize(jsonParser, null);

        assertEquals(123, question.getId());
        assertEquals("Which of the following SQL statements is correct?", question.getQuestion());
        assertNull(question.getDescription());
        assertNull(question.getExplanation());
        assertNull(question.getTip());
        assertEquals("SQL", question.getCategory());
        assertEquals(Difficulty.HARD, question.getDifficulty());
        assertFalse(question.getMultipleCorrectAnswers());
        assertEquals(1, question.getTags().size());
        assertEquals("SQL", question.getTags().get(0).getName());
        assertEquals(3, question.getAnswers().size());
    }
}
