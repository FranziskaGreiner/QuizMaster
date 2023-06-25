package franziska.greiner.util;

import franziska.greiner.model.Tag;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApiResponse {
    private Integer id;
    private String question;
    private String description;
    private Map<String, String> answers;
    private String multiple_correct_answers;
    private Map<String, String> correct_answers;
    private String explanation;
    private String tip;
    private List<Tag> tags;
    private String category;
    private String difficulty;
}
