package franziska.greiner.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import franziska.greiner.model.Answer;
import franziska.greiner.model.Difficulty;
import franziska.greiner.model.Question;
import franziska.greiner.model.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionDeserializer extends JsonDeserializer<Question> {

    @Override
    public Question deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);
        ApiResponse apiResponse = jsonParser.readValueAs(ApiResponse.class);
        Question question = new Question();

        // Set simple fields
        question.setId(apiResponse.getId());
        question.setQuestion(apiResponse.getQuestion());
        question.setDescription(apiResponse.getDescription());
        question.setExplanation(apiResponse.getExplanation());
        question.setTip(apiResponse.getTip());
        question.setCategory(apiResponse.getCategory());
        question.setDifficulty(Difficulty.valueOf(apiResponse.getDifficulty().toUpperCase()));

        // Convert tags
        List<Tag> tagsList = new ArrayList<>();
        JsonNode tagsNode = node.get("tags");
        if (tagsNode.isArray()) {
            for (JsonNode tagNode : tagsNode) {
                Tag tag = new Tag();
                tag.setName(tagNode.get("name").asText());
                tagsList.add(tag);
            }
        }
        question.setTags(tagsList);

        // Convert answers
        List<Answer> answers = new ArrayList<>();
        for (Map.Entry<String, String> entry : apiResponse.getAnswers().entrySet()) {
            Answer answer = new Answer();
            answer.setAnswer(entry.getValue());
            answer.setCorrect(Boolean.parseBoolean(apiResponse.getCorrectAnswers().get(entry.getKey() + "_correct")));
            answer.setQuestion(question);
            answers.add(answer);
        }
        question.setAnswers(answers);

        // Convert multiple_correct_answers
        question.setMultipleCorrectAnswers(Boolean.parseBoolean(apiResponse.getMultipleCorrectAnswers()));

        return question;
    }
}
