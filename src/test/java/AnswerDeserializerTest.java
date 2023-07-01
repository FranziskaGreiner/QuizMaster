import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import franziska.greiner.model.Answer;
import franziska.greiner.util.AnswerDeserializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AnswerDeserializerTest {

    @Test
    void testDeserialize() throws IOException {
        Map<String, String> answersMap = new HashMap<>();
        answersMap.put("1", "Answer 1");
        answersMap.put("2", "Answer 2");

        JsonParser jsonParser = Mockito.mock(JsonParser.class);
        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);

        when(jsonParser.readValueAs(Map.class)).thenReturn(answersMap);

        AnswerDeserializer answerDeserializer = new AnswerDeserializer();
        List<Answer> answers = answerDeserializer.deserialize(jsonParser, deserializationContext);

        assertEquals(2, answers.size(), "There should be 2 answers");
        assertEquals("Answer 1", answers.get(0).getAnswer(), "First answer should be 'Answer 1'");
        assertEquals("Answer 2", answers.get(1).getAnswer(), "Second answer should be 'Answer 2'");
    }
}
