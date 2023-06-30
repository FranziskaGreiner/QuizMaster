package franziska.greiner.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import franziska.greiner.model.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnswerDeserializer extends JsonDeserializer<List<Answer>> {
    @Override
    public List<Answer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Answer> answers = new ArrayList<>();

        Map<String, String> answersMap = p.readValueAs(Map.class);

        for (Map.Entry<String, String> entry : answersMap.entrySet()) {
            if (entry.getValue() != null) {
                Answer answer = new Answer();
                answer.setAnswer(entry.getValue());
                answers.add(answer);
            }
        }

        return answers;
    }
}
